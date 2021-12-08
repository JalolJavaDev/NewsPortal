package com.dailynews.Newsdaily.service;
import com.dailynews.Newsdaily.domen.Views;
import com.dailynews.Newsdaily.repository.ViewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ViewsServicesImpl implements ViewsServices {


    private final ViewsRepository viewsRepository;

    public ViewsServicesImpl(ViewsRepository viewsRepository) {
        this.viewsRepository = viewsRepository;
    }

    @Override
    public List<Views> getPopularNewsByViews() {
        return viewsRepository.getPopularNewsByViews();
    }
}
