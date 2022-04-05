package com.hexagrammers.DamPlay.Repositories;

import com.hexagrammers.DamPlay.Models.Notice;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoticeRepository extends CrudRepository<Notice,Integer> {
}
