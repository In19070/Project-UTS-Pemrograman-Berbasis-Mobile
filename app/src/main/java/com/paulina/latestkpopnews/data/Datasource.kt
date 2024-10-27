package com.paulina.latestkpopnews.data

import com.paulina.latestkpopnews.R
import com.paulina.latestkpopnews.model.LatestKpopNews
import com.paulina.latestkpopnews.model.KpopGroup

class Datasource {

    fun loadLatestKpopNews(): List<LatestKpopNews> {
        return listOf(
            LatestKpopNews(R.string.news_title_blackpink, R.drawable.blackpink),
            LatestKpopNews(R.string.news_title_bts, R.drawable.bts),
            LatestKpopNews(R.string.news_title_seventeen, R.drawable.seventeen)
        )
    }

    fun loadKpopGroups(): List<KpopGroup> {
        return listOf(
            KpopGroup(R.string.group_name_blackpink, R.drawable.blackpink),
            KpopGroup(R.string.group_name_exo, R.drawable.exo),
            KpopGroup(R.string.group_name_twice, R.drawable.twice)
        )
    }
}