package home.hunmukblog.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
//@WebMvcTest(PostControllerDocTest.class)
@AutoConfigureMockMvc
@AutoConfigureRestDocs(uriScheme = "https", uriHost = "api.hunmuk-blog.com", uriPort = 443)
@ExtendWith(RestDocumentationExtension.class)
public class InquiryControllerDocTest {

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
    @DisplayName("문의 리스트")
    public void test() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/api/inquiry")
                                .accept("application/json")
        )
        .andExpect(status().isOk())
        .andDo(print())
        .andDo(
                document("inquiry-list"
                        , preprocessResponse(prettyPrint()) // 테스트에 프리티 프린트 추가
                        , responseFields(
                                  fieldWithPath("[].id").type(JsonFieldType.NUMBER).description("문의 ID")
                                , fieldWithPath("[].title").type(JsonFieldType.STRING).description("문의 제목")
                                , fieldWithPath("[].content").type(JsonFieldType.STRING).description("문의 내용")
                                , fieldWithPath("[].regDt").type(JsonFieldType.STRING).optional().description("문의 등록일")
                        )
                )
        );
    }

    @Test
    @DisplayName("문의 상세")
    public void testDetail() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/api/inquiry/{id}", 1)
                        .accept("application/json")
                )
                .andExpect(status().isOk())
                .andDo(print())
                .andDo(
                        document("inquiry-detail"
                                , preprocessResponse(prettyPrint()) // 테스트에 프리티 프린트 추가
                                , responseFields(
                                        fieldWithPath("id").type(JsonFieldType.NUMBER).description("문의 ID")
                                        , fieldWithPath("title").type(JsonFieldType.STRING).description("문의 제목")
                                        , fieldWithPath("content").type(JsonFieldType.STRING).description("문의 내용")
                                        , fieldWithPath("viewCnt").type(JsonFieldType.NUMBER).optional().description("조회수")
                                        , fieldWithPath("regId").type(JsonFieldType.STRING).optional().description("문의 등록 ID")
                                        , fieldWithPath("regDt").type(JsonFieldType.STRING).optional().description("문의 등록일")
                                        , fieldWithPath("modId").type(JsonFieldType.STRING).optional().description("문의 수정 ID")
                                        , fieldWithPath("modDt").type(JsonFieldType.STRING).optional().description("문의 수정일 ")
                                )
                        )
                );

    }

    @Test
    public void commentList() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/api/inquiry/1/comment")
                        .accept("application/json")
                )
                .andExpect(status().isOk())
                .andDo(print())
                .andDo(
                        document("inquiry-comment-list"
                                , preprocessResponse(prettyPrint()) // 테스트에 프리티 프린트 추가
                                , responseFields(
                                        fieldWithPath("[].inquiryId").type(JsonFieldType.NUMBER).description("댓글 ID")
                                        , fieldWithPath("[].content").type(JsonFieldType.STRING).description("댓글 내용")
                                        , fieldWithPath("[].regDt").type(JsonFieldType.STRING).optional().description("댓글 등록일")
                                        , fieldWithPath("[].regId").type(JsonFieldType.STRING).optional().description("댓글 등록 ID")
                                )
                        )
                );

    }
}
