package home.hunmukblog.web.api.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/courses")
public class CourseController {

    @GetMapping("/search")
    public ResponseEntity<String> searchCourses(@RequestParam String keyword) throws JsonProcessingException {

        System.out.println(" ================================================== ");
        System.out.println(" ================================================== ");
        System.out.println(" ================================================== ");
        System.out.println(" ================================================== ");
        System.out.println(" ================================================== ");
        System.out.println(" ================================================== ");
        System.out.println(" ================================================== ");
        System.out.println(" ================================================== ");
        System.out.println(" ================================================== ");
        System.out.println(" ================================================== ");
        System.out.println(" ================================================== ");
        System.out.println(" ================================================== ");
        System.out.println(" ================================================== ");
        System.out.println(" ================================================== ");
        System.out.println(" ================================================== ");
        System.out.println(" ================================================== ");

        // 1. 더미 데이터 (예시)
        List<Course> courses = getDummyCourses(keyword);

        // 2. 결과를 Map 형태로 준비
        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("status", 200);

        // courses 리스트 생성
        List<Map<String, Object>> courseList = new ArrayList<>();
        for (Course course : courses) {
            Map<String, Object> courseMap = new HashMap<>();
            courseMap.put("courseId", course.getId());
            courseMap.put("courseName", course.getName());
            courseMap.put("type", course.getType());
            courseMap.put("code", course.getCode());

            // 참여자 목록 처리
            List<Map<String, String>> participants = new ArrayList<>();
            for (Member member : course.getParticipants()) {
                Map<String, String> participantMap = new HashMap<>();
                participantMap.put("userId", member.getId().toString());
                participantMap.put("userName", member.getName());
                participantMap.put("email", member.getEmail());
                participants.add(participantMap);
            }
            courseMap.put("참여자", participants);

            courseList.add(courseMap);
        }

        responseMap.put("data", Map.of(
                "courses", courseList,
                "pagination", Map.of(
                        "currentPage", 1, // 예시, 실제 페이지 값으로 변경
                        "totalPages", 5,  // 예시
                        "totalItems", 50  // 예시
                )
        ));

        // 3. JSON으로 변환해서 리턴
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonResponse = objectMapper.writeValueAsString(responseMap);

        return ResponseEntity.ok(jsonResponse);
    }

    // 더미 데이터 생성 (예시)
    private List<Course> getDummyCourses(String keyword) {
        List<Course> courses = new ArrayList<>();

        // 더미 참여자 생성
        Member member1 = new Member(1L, "홍길동", "hong@example.com");
        Member member2 = new Member(2L, "김철수", "kim@example.com");

        // 더미 교육 과정 생성
        Course course = new Course("1751520", "2025년 마약류 및 약물오남용 예방교육", "ONLINE", "string");
        course.setParticipants(Arrays.asList(member1, member2));

        courses.add(course);

        // 키워드에 맞는 필터링 로직 예시 (실제로는 키워드에 맞게 검색할 로직이 필요)
        if (keyword != null && !keyword.isEmpty()) {
            courses = courses.stream()
                    .filter(c -> c.getName().contains(keyword))  // 키워드가 포함된 교육 과정만 반환
                    .collect(Collectors.toList());
        }

        return courses;
    }

    public class Course {
        private String id;
        private String name;
        private String type;
        private String code;
        private List<Member> participants;

        // Constructor, Getters, Setters
        public Course(String id, String name, String type, String code) {
            this.id = id;
            this.name = name;
            this.type = type;
            this.code = code;
            this.participants = new ArrayList<>();
        }

        public void setParticipants(List<Member> participants) {
            this.participants = participants;
        }

        public String getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getType() {
            return type;
        }

        public String getCode() {
            return code;
        }

        public List<Member> getParticipants() {
            return participants;
        }
    }

    public class Member {
        private Long id;
        private String name;
        private String email;

        // Constructor, Getters, Setters
        public Member(Long id, String name, String email) {
            this.id = id;
            this.name = name;
            this.email = email;
        }

        public Long getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getEmail() {
            return email;
        }
    }
}