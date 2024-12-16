package home.hunmukblog.controller;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.core.AutoConfigureCache;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.restdocs.payload.PayloadDocumentation;
import org.springframework.restdocs.request.RequestDocumentation;
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
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureRestDocs(uriScheme = "https", uriHost = "api.hunmuk-blog.com", uriPort = 443)
@ExtendWith(RestDocumentationExtension.class)
public class MemberControllerDocTest {

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
    @DisplayName("리스트")
    public void test() throws Exception {

        // expecte
        this.mockMvc.perform(get("/api/member")
                        .accept("application/json")
                )
                .andExpect(status().isOk())
                .andDo(print())
                .andDo(document("member-list",
                        preprocessResponse(prettyPrint()), // 테스트에 프리티 프린트 추가
                        responseFields(
                                fieldWithPath("[].id").description("일련번호"),
                                fieldWithPath("[].loginId").optional().description("아이디"),
                                fieldWithPath("[].name").description("이름"),
                                fieldWithPath("[].age").description("나이"),
                                fieldWithPath("[].regDt").description("등록일"),
                                fieldWithPath("[].regId").description("등록자")
                        )
                ))
                ;
    }

    @Test
    @DisplayName("상세")
    public void test2() throws Exception {

        // expecte
        this.mockMvc.perform(RestDocumentationRequestBuilders.get("/api/member/{id}", 1)
                        .accept("application/json")
                )
                .andExpect(status().isOk())
                .andDo(print())
                .andDo(document("member-detail",
                        preprocessResponse(prettyPrint()), // 테스트에 프리티 프린트 추가
                        pathParameters(
                                RequestDocumentation.parameterWithName("id").description("아이디")
                        ),
                        responseFields(
                                fieldWithPath("id").description("일련번호"),
                                fieldWithPath("loginId").optional().description("아이디"),
                                fieldWithPath("name").description("이름"),
                                fieldWithPath("age").description("나이"),
                                fieldWithPath("regDt").description("등록일"),
                                fieldWithPath("regId").description("등록자")
                        )
                ));
    }


}
