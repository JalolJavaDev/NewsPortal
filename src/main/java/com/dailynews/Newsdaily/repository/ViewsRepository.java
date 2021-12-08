package com.dailynews.Newsdaily.repository;
import com.dailynews.Newsdaily.domen.Views;

import java.util.List;

public interface ViewsRepository {
    List<Views> getPopularNewsByViews();
}