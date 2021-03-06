package fr.zabricraft.delta.sections;

import android.view.View;
import android.view.ViewGroup;

import org.javatuples.Pair;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;
import fr.zabricraft.delta.R;
import fr.zabricraft.delta.views.HeaderCell;
import fr.zabricraft.delta.views.InputCell;
import io.github.luizgrp.sectionedrecyclerviewadapter.Section;
import io.github.luizgrp.sectionedrecyclerviewadapter.SectionParameters;
import io.github.luizgrp.sectionedrecyclerviewadapter.utils.EmptyViewHolder;

public class InputsSection extends Section {

    private final InputsContainer container;

    public InputsSection(InputsContainer container) {
        super(SectionParameters.builder().itemViewWillBeProvided().headerViewWillBeProvided().build());

        this.container = container;
    }

    public int getContentItemsTotal() {
        return container.getInputs().size();
    }

    public View getItemView(ViewGroup parent) {
        return new InputCell(parent.getContext());
    }

    public View getHeaderView(ViewGroup parent) {
        return new HeaderCell(parent.getContext());
    }

    public RecyclerView.ViewHolder getItemViewHolder(View view) {
        // return a custom instance of ViewHolder for the items of this section
        return new EmptyViewHolder(view);
    }

    public void onBindItemViewHolder(RecyclerView.ViewHolder holder, final int position) {
        EmptyViewHolder itemHolder = (EmptyViewHolder) holder;
        Pair<String, String> input = container.getInputs().get(position);

        // bind your view here
        if (itemHolder.itemView instanceof InputCell) {
            ((InputCell) itemHolder.itemView).with(input, container);
        }
    }

    public RecyclerView.ViewHolder getHeaderViewHolder(View view) {
        // Check if it's a headerCell
        if (view instanceof HeaderCell) {
            ((HeaderCell) view).with(R.string.inputs);
        }

        // return an empty instance of ViewHolder for the headers of this section
        return new EmptyViewHolder(view);
    }

    // Container interface
    public interface InputsContainer {
        List<Pair<String, String>> getInputs();

        void inputChanged(Pair<String, String> input);
    }

}
