package home.hunmukblog.controller;

import netscape.javascript.JSObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.restdocs.payload.PayloadDocumentation;
import org.springframework.restdocs.request.RequestDocumentation;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.result.StatusResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.payload.JsonFieldType.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.snippet.Attributes.key;

@SpringBootTest
//@WebMvcTest(PostControllerDocTest.class)
@AutoConfigureMockMvc
@AutoConfigureRestDocs(uriScheme = "https", uriHost = "api.hunmuk-blog.com", uriPort = 443)
@ExtendWith(RestDocumentationExtension.class)
public class PostControllerDocTest {

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setUp(WebApplicationContext webApplicationContext, RestDocumentationContextProvider restDocumentation) {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .apply(documentationConfiguration(restDocumentation)
                                .operationPreprocessors()
                                .withRequestDefaults(prettyPrint())
                            )
                .build();
    }

    @Test
    @DisplayName("리스트 테스트")
    public void test() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/api/post")
                        .param("page", "0")
                        .param("size", "10")
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(document("post-list",
                        preprocessResponse(prettyPrint()), // 테스트에 프리티 프린트 추가
                        responseFields(
                                fieldWithPath("pageable").type(JsonFieldType.OBJECT).description("페이지 정보"),
                                fieldWithPath("pageable.pageNumber").type(JsonFieldType.NUMBER).description("현재 페이지 번호 (0부터 시작)"),
                                fieldWithPath("pageable.pageSize").type(JsonFieldType.NUMBER).description("페이지 크기"),
                                fieldWithPath("pageable.offset").type(JsonFieldType.NUMBER).description("현재 페이지의 시작 오프셋"),
                                fieldWithPath("pageable.paged").type(JsonFieldType.BOOLEAN).description("페이징 여부"),
                                fieldWithPath("pageable.unpaged").type(JsonFieldType.BOOLEAN).description("비페이징 여부"),
                                fieldWithPath("pageable.sort").type(JsonFieldType.OBJECT).description("정렬 정보"),
                                fieldWithPath("pageable.sort.empty").type(JsonFieldType.BOOLEAN).description("정렬 값이 비었는지 여부"),
                                fieldWithPath("pageable.sort.sorted").type(JsonFieldType.BOOLEAN).description("정렬이 되었는지 여부"),
                                fieldWithPath("pageable.sort.unsorted").type(JsonFieldType.BOOLEAN).description("정렬되지 않았는지 여부"),

                                fieldWithPath("last").type(JsonFieldType.BOOLEAN).description("마지막 페이지 여부"),
                                fieldWithPath("totalPages").type(JsonFieldType.NUMBER).description("총 페이지 수"),
                                fieldWithPath("totalElements").type(JsonFieldType.NUMBER).description("총 요소 수"),
                                fieldWithPath("first").type(JsonFieldType.BOOLEAN).description("첫 번째 페이지 여부"),
                                fieldWithPath("size").type(JsonFieldType.NUMBER).description("페이지 크기"),
                                fieldWithPath("number").type(JsonFieldType.NUMBER).description("현재 페이지 번호"),
                                fieldWithPath("sort").type(JsonFieldType.OBJECT).description("정렬 정보"),
                                fieldWithPath("sort.empty").type(JsonFieldType.BOOLEAN).description("정렬 값이 비었는지 여부"),
                                fieldWithPath("sort.sorted").type(JsonFieldType.BOOLEAN).description("정렬이 되었는지 여부"),
                                fieldWithPath("sort.unsorted").type(JsonFieldType.BOOLEAN).description("정렬되지 않았는지 여부"),
                                fieldWithPath("numberOfElements").type(JsonFieldType.NUMBER).description("현재 페이지 요소 수"),
                                fieldWithPath("empty").type(JsonFieldType.BOOLEAN).description("응답이 비었는지 여부")
                        ).andWithPrefix("content[].", // content 배열에 대한 prefix
                                fieldWithPath("id").type(JsonFieldType.NUMBER).description("게시물 ID"),
                                fieldWithPath("title").type(JsonFieldType.STRING).description("게시물 제목"),
                                fieldWithPath("content").type(JsonFieldType.STRING).description("게시물 내용"),
                                fieldWithPath("viewCnt").type(JsonFieldType.NUMBER).optional().description("조회수"),
                                fieldWithPath("regDt").type(JsonFieldType.STRING).description("등록일"),
                                fieldWithPath("regId").type(JsonFieldType.STRING).description("등록한 사용자 ID"),
                                fieldWithPath("modDt").type(JsonFieldType.STRING).optional().description("수정일"),
                                fieldWithPath("modId").type(JsonFieldType.STRING).optional().description("수정한 사용자 ID")
                        )
                ));

    }
    @Test
    @DisplayName("상세 테스트")
    public void test2() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/api/post/9"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(
                        document("post-detail",       // 이걸 작성해줘야 build/generated-snippets 에 문서가 생성됨
                                preprocessResponse(prettyPrint()), // 테스트에 프리티 프린트 추가
                                responseFields(
                                                fieldWithPath("id").type(NUMBER).description("일련번호").attributes(key("generated").value("true")), // 자동생성 여부 명시,
                                                fieldWithPath("title").type(STRING).description("제목"),
                                                fieldWithPath("content").type(STRING).description("내용").attributes(key("generated").value("true")), // 필수 여부 명시,
                                                fieldWithPath("viewCnt").type(NUMBER).optional().description("조회수"),
                                                fieldWithPath("regDt").type(STRING).description("등록일"),
                                                fieldWithPath("regId").type(STRING).description("등록아이디"),
                                                fieldWithPath("modDt").type(STRING).optional().description("수정일"),
                                                fieldWithPath("modId").type(STRING).optional().description("수정아이디")
                                        )
                                )
                );

    }
}
