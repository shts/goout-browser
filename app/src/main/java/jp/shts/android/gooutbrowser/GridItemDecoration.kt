package jp.shts.android.gooutbrowser

import android.graphics.Rect
import android.support.v7.widget.RecyclerView
import android.view.View

class GridItemDecoration : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect?, view: View?, parent: RecyclerView?, state: RecyclerView.State?) {
        super.getItemOffsets(outRect, view, parent, state)
        outRect?.set(8, 8, 8, 8)
    }
}
