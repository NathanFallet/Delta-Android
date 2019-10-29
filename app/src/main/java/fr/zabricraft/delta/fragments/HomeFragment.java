package fr.zabricraft.delta.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import fr.zabricraft.delta.R;
import fr.zabricraft.delta.extensions.AlgorithmExtension;
import fr.zabricraft.delta.sections.AlgorithmsSection;
import fr.zabricraft.delta.utils.Algorithm;
import fr.zabricraft.delta.utils.Database;
import io.github.luizgrp.sectionedrecyclerviewadapter.SectionedRecyclerViewAdapter;

public class HomeFragment extends Fragment {

    private List<Algorithm> myalgorithms;
    private List<Algorithm> downloads;

    private RecyclerView recyclerView;

    public void loadAlgorithms() {
        // Clear previous data
        myalgorithms.clear();
        downloads.clear();

        // Retrieve algorithms from database
        List<Algorithm> algorithms = Database.getInstance(getActivity()).getAlgorithms();

        // Iterate them
        for (Algorithm algorithm : algorithms) {
            // Check if owned or downloaded
            if (algorithm.isOwner()) {
                // Add to My algorithms
                myalgorithms.add(algorithm);
            } else {
                // Add to Downloads
                downloads.add(algorithm);
            }
        }

        // If downloads are empty
        if (downloads.isEmpty()) {
            // TODO: download them from API and save them
            for (Algorithm algorithm : AlgorithmExtension.defaults) {
                // Save it locally
                Database.getInstance(getActivity()).addAlgorithm(algorithm);
            }

            // Reload algorithms
            loadAlgorithms();
        }

        // Update recyclerView
        recyclerView.getAdapter().notifyDataSetChanged();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Init lists
        myalgorithms = new ArrayList<>();
        downloads = new ArrayList<>();

        // Create the view
        recyclerView = new RecyclerView(getActivity());
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);

        // Initialize sections
        SectionedRecyclerViewAdapter sectionAdapter = new SectionedRecyclerViewAdapter();
        sectionAdapter.addSection(new AlgorithmsSection(R.string.myalgorithms, myalgorithms, ((AlgorithmsSection.AlgorithmLoader) getActivity())));
        sectionAdapter.addSection(new AlgorithmsSection(R.string.downloads, downloads, ((AlgorithmsSection.AlgorithmLoader) getActivity())));

        // Bind adapter to recyclerView
        recyclerView.setAdapter(sectionAdapter);

        // Load algorithms
        loadAlgorithms();

        return recyclerView;
    }

}