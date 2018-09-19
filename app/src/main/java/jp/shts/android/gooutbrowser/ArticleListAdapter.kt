package jp.shts.android.gooutbrowser

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import jp.shts.android.gooutbrowser.data.Article
import kotlinx.android.synthetic.main.list_item_article.view.*

class ArticleListAdapter : RecyclerView.Adapter<ArticleListAdapter.ArticleViewHolder>() {

    interface OnItemClickListener {
        fun onClick(data: Article)
        fun onLongClick(data: Article)
    }

    interface OnMenuItemClickListener {
        fun onClickFavorite(data: Article)
    }

    var onItemClickListener: OnItemClickListener? = null
    var onMenuItemClickListener: OnMenuItemClickListener? = null

    private val dataList = ArrayList<Article>()

    fun add(data: List<Article>) {
        dataList.addAll(ArrayList<Article>(data)) // cast
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder = ArticleViewHolder(parent)

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holderArticle: ArticleViewHolder, position: Int) {
        holderArticle.bind(dataList[position], onItemClickListener, onMenuItemClickListener)
    }

    class ArticleViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.list_item_article, parent, false)
    ) {
        fun bind(
                data: Article,
                onItemClickListener: OnItemClickListener?,
                onMenuItemClickListener: OnMenuItemClickListener?
        ) {
            itemView.setOnClickListener { onItemClickListener?.onClick(data) }
            itemView.setOnLongClickListener {
                onItemClickListener?.onLongClick(data)
                return@setOnLongClickListener true
            }
            itemView.favorite.setOnClickListener { onMenuItemClickListener?.onClickFavorite(data) }
            itemView.favorite.setBackgroundResource(
                    if (data.favorite) {
                        R.drawable.ic_star_yellow
                    } else {
                        R.drawable.ic_star_border_white
                    }
            )
            itemView.title.text = data.title
            itemView.date.text = data.dateText
            Glide.with(itemView.context)
                    .load(data.thumb)
                    .apply(RequestOptions().centerCrop())
                    .into(itemView.thumb)
        }
    }
}
