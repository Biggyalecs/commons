package ro.mentenantapc.commons.adapters

import android.util.TypedValue
import android.view.Menu
import android.view.ViewGroup
import ro.mentenantapc.commons.R
import ro.mentenantapc.commons.activities.BaseSimpleActivity
import ro.mentenantapc.commons.databinding.FilepickerFavoriteBinding
import ro.mentenantapc.commons.extensions.getTextSize
import ro.mentenantapc.commons.views.MyRecyclerView

class FilepickerFavoritesAdapter(
    activity: BaseSimpleActivity, val paths: List<String>, recyclerView: MyRecyclerView,
    itemClick: (Any) -> Unit
) : MyRecyclerViewAdapter(activity, recyclerView, itemClick) {

    private var fontSize = 0f

    init {
        fontSize = activity.getTextSize()
    }

    override fun getActionMenuId() = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = createViewHolder(R.layout.filepicker_favorite, parent)

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val path = paths[position]
        holder.bindView(path, allowSingleClick = true, allowLongClick = false) { itemView, adapterPosition ->
            setupView(FilepickerFavoriteBinding.bind(itemView), path)
        }
        bindViewHolder(holder)
    }

    override fun getItemCount() = paths.size

    override fun prepareActionMode(menu: Menu) {}

    override fun actionItemPressed(id: Int) {}

    override fun getSelectableItemCount() = paths.size

    override fun getIsItemSelectable(position: Int) = false

    override fun getItemKeyPosition(key: Int) = paths.indexOfFirst { it.hashCode() == key }

    override fun getItemSelectionKey(position: Int) = paths[position].hashCode()

    override fun onActionModeCreated() {}

    override fun onActionModeDestroyed() {}

    private fun setupView(view: FilepickerFavoriteBinding, path: String) {
        view.apply {
            filepickerFavoriteLabel.text = path
            filepickerFavoriteLabel.setTextColor(textColor)
            filepickerFavoriteLabel.setTextSize(TypedValue.COMPLEX_UNIT_PX, fontSize)
        }
    }
}
