package com.lambdaschool.starthere.services;

import com.lambdaschool.starthere.repository.SectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "sectionService")
public class SectionServiceImpl implements SectionService
{
    @Autowired
    private SectionRepository sectionrepos;
}
