package com.j6d7.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.j6d7.entity.Role;

public interface RoleDAO extends JpaRepository<Role, String> { }
