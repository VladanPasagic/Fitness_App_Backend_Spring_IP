package etf.unibl.org.ip.ipbackend.services;

import etf.unibl.org.ip.ipbackend.models.dtos.Category;

import java.util.List;

public interface CategoryService {
    List<Category> getAll();
}
