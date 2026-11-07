
package com.example.teammember.repository;

import com.example.teammember.entity.TeamMember;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TeamMemberRepository
        extends JpaRepository<TeamMember, Long> {

    // Derived Query Method 1
    List<TeamMember> findByName(String name);

    // Derived Query Method 2
    List<TeamMember> findByType(String type);
}