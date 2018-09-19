package jp.shts.android.gooutbrowser

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.support.customtabs.CustomTabsIntent
import android.support.v4.app.Fragment
import android.support.v7.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import jp.shts.android.gooutbrowser.data.Article
import kotlinx.android.synthetic.main.fragment_article.*

class ArticleListFragment : Fragment(), ArticleListAdapter.OnItemClickListener, ArticleListAdapter.OnMenuItemClickListener {
    companion object {
        private const val EXTRA_CATEGORY = "extra_category"

        fun newInstance(category: Category) = ArticleListFragment().apply {
            arguments = Bundle().apply { putInt(EXTRA_CATEGORY, category.ordinal) }
        }
    }

    private val category by lazy {
        Category.values()[arguments?.getInt(EXTRA_CATEGORY) ?: -1]
    }

    private val viewModel by lazy {
        ViewModelProviders.of(this).get(ArticleViewModel::class.java)
    }

    private val adapter = ArticleListAdapter().apply {
        onItemClickListener = this@ArticleListFragment
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        viewModel.getLiveData(category).observe(this, Observer {
//            if (it != null) {
//                adapter.add(it)
//                adapter.notifyDataSetChanged()
//            }
//        })
        viewModel.liveData(category).observe(this, Observer {
            if (it != null) {
                adapter.add(it)
                adapter.notifyDataSetChanged()
            }
        })
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_article, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView.addItemDecoration(GridItemDecoration())
        recyclerView.adapter = adapter
    }

    override fun onStart() {
        super.onStart()
        viewModel.get(category)
    }

    override fun onClick(data: Article) {
        CustomTabsIntent.Builder()
                .setShowTitle(true)
                .setToolbarColor(Color.BLACK)
                .setSecondaryToolbarColor(Color.BLACK)
                .setStartAnimations(requireContext(), R.anim.pull_in_right, R.anim.push_out_left)
                .setExitAnimations(requireContext(), R.anim.pull_in_left, R.anim.push_out_right)
                .build()
                .launchUrl(requireContext(), Uri.parse(data.link))
    }

    override fun onLongClick(data: Article) {
        AlertDialog.Builder(requireContext())
                .setItems(arrayOf("お気に入り", "何もしない"), { _, which ->
                    when (which) {
                        0 -> {
                            viewModel.favorite(data)
                        }
                    }
                })
                .show()
    }

    override fun onClickFavorite(data: Article) {
        viewModel.favToggle(data)
    }
}

