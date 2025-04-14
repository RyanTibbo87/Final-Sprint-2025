package com.bstproject.repository;

import com.bstproject.model.TreeData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TreeDataRepository extends JpaRepository<TreeData, Long> {
}
