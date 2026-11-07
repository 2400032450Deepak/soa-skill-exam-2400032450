package com.example.teammember.service;

import com.example.teammember.dto.TeamMemberDTO;
import com.example.teammember.entity.TeamMember;

import java.util.List;

public interface TeamMemberService {

    TeamMember insertTeamMember(TeamMemberDTO dto);

    TeamMember updateTeamMember(Long id, TeamMemberDTO dto);

    void deleteTeamMember(Long id);

    List<TeamMember> getAllTeamMembers();

    TeamMember getTeamMemberById(Long id);

    List<TeamMember> findByName(String name);

    List<TeamMember> findByType(String type);
}
