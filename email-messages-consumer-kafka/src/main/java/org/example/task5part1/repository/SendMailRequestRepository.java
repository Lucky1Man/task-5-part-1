package org.example.task5part1.repository;

import org.example.task5part1.document.SendMailRequest;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SendMailRequestRepository extends ElasticsearchRepository<SendMailRequest, String> {
}
