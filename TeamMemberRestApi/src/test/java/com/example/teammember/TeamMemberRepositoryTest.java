package com.example.teammember;

import com.example.teammember.entity.TeamMember;
import com.example.teammember.repository.TeamMemberRepository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class TeamMemberRepositoryTest {

    @Autowired
    private TeamMemberRepository repository;

    // Test 1: Insert Team Member
    @Test
    void testInsertTeamMember() {

        TeamMember member = new TeamMember();

        member.setName("Deepak");
        member.setType("Developer");
        member.setStatus("Active");
        member.setLocation("India");

        TeamMember savedMember = repository.save(member);

        assertNotNull(savedMember.getTeamMemberId());
        assertEquals("Deepak", savedMember.getName());

    }

    // Test 2: Find Team Member By ID
    @Test
    void testFindById() {

        TeamMember member = new TeamMember();

        member.setName("Rahul");
        member.setType("Tester");
        member.setStatus("Active");
        member.setLocation("Hyderabad");

        TeamMember savedMember = repository.save(member);

        Optional<TeamMember> result =
                repository.findById(savedMember.getTeamMemberId());

        assertTrue(result.isPresent());
        assertEquals("Rahul", result.get().getName());

    }

    // Test 3: Find Team Member By Name
    @Test
    void testFindByName() {

        TeamMember member = new TeamMember();

        member.setName("Deepak");
        member.setType("Developer");
        member.setStatus("Active");
        member.setLocation("India");

        repository.save(member);

        List<TeamMember> result =
                repository.findByName("Deepak");

        assertFalse(result.isEmpty());
        assertEquals("Deepak", result.get(0).getName());

    }

    // Test 4: Find Team Member By Type
    @Test
    void testFindByType() {

        TeamMember member = new TeamMember();

        member.setName("Kumar");
        member.setType("Developer");
        member.setStatus("Active");
        member.setLocation("Chennai");

        repository.save(member);

        List<TeamMember> result =
                repository.findByType("Developer");

        assertFalse(result.isEmpty());
        assertEquals("Developer", result.get(0).getType());

    }

    // Test 5: Delete Team Member
    @Test
    void testDeleteTeamMember() {

        TeamMember member = new TeamMember();

        member.setName("Arun");
        member.setType("Designer");
        member.setStatus("Active");
        member.setLocation("Delhi");

        TeamMember savedMember = repository.save(member);

        Long id = savedMember.getTeamMemberId();

        repository.deleteById(id);

        Optional<TeamMember> result =
                repository.findById(id);

        assertFalse(result.isPresent());

    }

}