package fr.zabricraft.delta.sections;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import fr.zabricraft.delta.R;
import fr.zabricraft.delta.utils.EditorLine;
import fr.zabricraft.delta.views.HeaderCell;
import fr.zabricraft.delta.views.LabelCell;
import io.github.luizgrp.sectionedrecyclerviewadapter.Section;
import io.github.luizgrp.sectionedrecyclerviewadapter.SectionParameters;
import io.github.luizgrp.sectionedrecyclerviewadapter.SectionedRecyclerViewAdapter;

public class EditorLinesSection extends Section {

    private final EditorLinesContainer container;

    public EditorLinesSection(EditorLinesContainer container) {
        super(SectionParameters.builder().itemViewWillBeProvided().headerViewWillBeProvided().build());

        this.container = container;
    }

    public int getContentItemsTotal() {
        return container.editorLinesCount();
    }

    public View getItemView(ViewGroup parent) {
        return new LabelCell(parent.getContext());
    }

    public View getHeaderView(ViewGroup parent) {
        return new HeaderCell(parent.getContext(), false);
    }

    public RecyclerView.ViewHolder getItemViewHolder(View view) {
        // return a custom instance of ViewHolder for the items of this section
        return new SectionedRecyclerViewAdapter.EmptyViewHolder(view);
    }

    public void onBindItemViewHolder(RecyclerView.ViewHolder holder, final int position) {
        final SectionedRecyclerViewAdapter.EmptyViewHolder itemHolder = (SectionedRecyclerViewAdapter.EmptyViewHolder) holder;

        // bind your view here
        if (itemHolder.itemView instanceof LabelCell) {

        }
    }

    public RecyclerView.ViewHolder getHeaderViewHolder(View view) {
        // Check if it's a headerCell
        if (view instanceof HeaderCell) {
            ((HeaderCell) view).with(R.string.instructions);
        }

        // return an empty instance of ViewHolder for the headers of this section
        return new SectionedRecyclerViewAdapter.EmptyViewHolder(view);
    }

    // Container interface
    public interface EditorLinesContainer {
        List<EditorLine> toEditorLines();

        int editorLinesCount();
    }

}
