package com.example.teammember.service;

import com.example.teammember.dto.TeamMemberDTO;
import com.example.teammember.entity.TeamMember;
import com.example.teammember.repository.TeamMemberRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamMemberServiceImpl implements TeamMemberService {

    private final TeamMemberRepository repository;

    public TeamMemberServiceImpl(TeamMemberRepository repository) {
        this.repository = repository;
    }

    @Override
    public TeamMember insertTeamMember(TeamMemberDTO dto) {

        TeamMember teamMember = new TeamMember();

        teamMember.setName(dto.getName());
        teamMember.setType(dto.getType());
        teamMember.setStatus(dto.getStatus());
        teamMember.setLocation(dto.getLocation());

        return repository.save(teamMember);
    }

    @Override
    public TeamMember updateTeamMember(Long id, TeamMemberDTO dto) {

        TeamMember existingMember = repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Team Member not found with ID: " + id));

        existingMember.setName(dto.getName());
        existingMember.setType(dto.getType());
        existingMember.setStatus(dto.getStatus());
        existingMember.setLocation(dto.getLocation());

        return repository.save(existingMember);
    }

    @Override
    public void deleteTeamMember(Long id) {

        if (!repository.existsById(id)) {
            throw new RuntimeException(
                    "Team Member not found with ID: " + id);
        }

        repository.deleteById(id);
    }

    @Override
    public List<TeamMember> getAllTeamMembers() {
        return repository.findAll();
    }

    @Override
    public TeamMember getTeamMemberById(Long id) {

        return repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Team Member not found with ID: " + id));
    }

    @Override
    public List<TeamMember> findByName(String name) {
        return repository.findByName(name);
    }

    @Override
    public List<TeamMember> findByType(String type) {
        return repository.findByType(type);
    }
}
