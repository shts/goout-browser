package jp.shts.android.gooutbrowser

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

class CategoryViewPager(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment = ArticleListFragment.newInstance(Category.values()[position])

    override fun getCount(): Int = Category.values().size

    override fun getPageTitle(position: Int) = Category.values()[position].name
}