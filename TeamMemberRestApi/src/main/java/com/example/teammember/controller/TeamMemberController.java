
package com.example.teammember.controller;

import com.example.teammember.dto.TeamMemberDTO;
import com.example.teammember.entity.TeamMember;
import com.example.teammember.service.TeamMemberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/team-members")
@Tag(name = "Team Member API",
     description = "REST APIs for Team Member management")
public class TeamMemberController {

    private final TeamMemberService service;

    public TeamMemberController(TeamMemberService service) {
        this.service = service;
    }

    @PostMapping
    @Operation(summary = "Insert Team Member")
    public ResponseEntity<TeamMember> insertTeamMember(
            @Valid @RequestBody TeamMemberDTO dto) {

        TeamMember savedMember =
                service.insertTeamMember(dto);

        return new ResponseEntity<>(
                savedMember,
                HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update Team Member")
    public ResponseEntity<TeamMember> updateTeamMember(
            @PathVariable Long id,
            @Valid @RequestBody TeamMemberDTO dto) {

        TeamMember updatedMember =
                service.updateTeamMember(id, dto);

        return ResponseEntity.ok(updatedMember);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete Team Member")
    public ResponseEntity<String> deleteTeamMember(
            @PathVariable Long id) {

        service.deleteTeamMember(id);

        return ResponseEntity.ok(
                "Team Member deleted successfully with ID: " + id);
    }

    @GetMapping
    @Operation(summary = "Display All Team Members")
    public ResponseEntity<List<TeamMember>> getAllTeamMembers() {

        return ResponseEntity.ok(
                service.getAllTeamMembers());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Display Team Member By ID")
    public ResponseEntity<TeamMember> getTeamMemberById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                service.getTeamMemberById(id));
    }

    @GetMapping("/search/name/{name}")
    @Operation(summary = "Find Team Members By Name")
    public ResponseEntity<List<TeamMember>> findByName(
            @PathVariable String name) {

        return ResponseEntity.ok(
                service.findByName(name));
    }

    @GetMapping("/search/type/{type}")
    @Operation(summary = "Find Team Members By Type")
    public ResponseEntity<List<TeamMember>> findByType(
            @PathVariable String type) {

        return ResponseEntity.ok(
                service.findByType(type));
    }
}
