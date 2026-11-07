package com.example.teammember;

import com.example.teammember.dto.TeamMemberDTO;
import com.example.teammember.entity.TeamMember;
import com.example.teammember.repository.TeamMemberRepository;
import com.example.teammember.service.TeamMemberService;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(properties = {
        "spring.datasource.url=jdbc:h2:mem:integrationdb;DB_CLOSE_DELAY=-1",
        "spring.datasource.driver-class-name=org.h2.Driver",
        "spring.datasource.username=sa",
        "spring.datasource.password=",
        "spring.jpa.hibernate.ddl-auto=create-drop",
        "spring.jpa.database-platform=org.hibernate.dialect.H2Dialect"
})
class TeamMemberIntegrationTest {

    @Autowired
    private TeamMemberService service;

    @Autowired
    private TeamMemberRepository repository;

    // Test 1: Insert Team Member
    @Test
    void testInsertTeamMember() {

        TeamMemberDTO dto = new TeamMemberDTO();

        dto.setName("Deepak");
        dto.setType("Developer");
        dto.setStatus("Active");
        dto.setLocation("India");

        TeamMember result = service.insertTeamMember(dto);

        assertNotNull(result.getTeamMemberId());
        assertEquals("Deepak", result.getName());

    }

    // Test 2: Display All Team Members
    @Test
    void testGetAllTeamMembers() {

        TeamMemberDTO dto = new TeamMemberDTO();

        dto.setName("Rahul");
        dto.setType("Tester");
        dto.setStatus("Active");
        dto.setLocation("Hyderabad");

        service.insertTeamMember(dto);

        List<TeamMember> result =
                service.getAllTeamMembers();

        assertFalse(result.isEmpty());

    }

    // Test 3: Display Team Member By ID
    @Test
    void testGetTeamMemberById() {

        TeamMemberDTO dto = new TeamMemberDTO();

        dto.setName("Arun");
        dto.setType("Developer");
        dto.setStatus("Active");
        dto.setLocation("Delhi");

        TeamMember savedMember =
                service.insertTeamMember(dto);

        TeamMember result =
                service.getTeamMemberById(
                        savedMember.getTeamMemberId());

        assertEquals("Arun", result.getName());

    }

    // Test 4: Update Team Member
    @Test
    void testUpdateTeamMember() {

        TeamMemberDTO dto = new TeamMemberDTO();

        dto.setName("Kumar");
        dto.setType("Developer");
        dto.setStatus("Active");
        dto.setLocation("India");

        TeamMember savedMember =
                service.insertTeamMember(dto);

        TeamMemberDTO updateDTO = new TeamMemberDTO();

        updateDTO.setName("Kumar Updated");
        updateDTO.setType("Tester");
        updateDTO.setStatus("Active");
        updateDTO.setLocation("Chennai");

        TeamMember updatedMember =
                service.updateTeamMember(
                        savedMember.getTeamMemberId(),
                        updateDTO);

        assertEquals("Kumar Updated", updatedMember.getName());
        assertEquals("Tester", updatedMember.getType());

    }

    // Test 5: Delete Team Member
    @Test
    void testDeleteTeamMember() {

        TeamMemberDTO dto = new TeamMemberDTO();

        dto.setName("Test User");
        dto.setType("Developer");
        dto.setStatus("Active");
        dto.setLocation("India");

        TeamMember savedMember =
                service.insertTeamMember(dto);

        Long id = savedMember.getTeamMemberId();

        service.deleteTeamMember(id);

        assertFalse(repository.existsById(id));

    }

}