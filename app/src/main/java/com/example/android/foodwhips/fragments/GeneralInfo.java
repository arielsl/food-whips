package com.example.android.foodwhips.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

import com.example.android.foodwhips.R;

public class GeneralInfo extends Fragment{
    private TextView mTimeTaken;
    private TextView mRecipeServings;
    private TextView mCourses;
    private TextView mCuisines;
    private TextView mFlavors;

    static final String TAG = "GeneralInfoFragment";

    private static final String RECIPE_TIME_VALUE = "recipe_time";
    private static final String RECIPE_SERVINGS_VALUE = "recipe_servings";
    private static final String RECIPE_COURSES = "recipe_courses";
    private static final String RECIPE_CUISINES = "recipe_cuisines";
    private static final String RECIPE_FLAVORS = "recipe_flavors";

    public GeneralInfo() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_general_info, container, false);

        //String recipe_id = getArguments().getString("recipe_id");
        //Log.v(TAG, "SUCCESSFULLY PASSED ID: " + recipe_id);

        String recipe_time = this.getArguments().getString(RECIPE_TIME_VALUE);
        String recipe_servings = this.getArguments().getString(RECIPE_SERVINGS_VALUE);
        String recipe_courses = this.getArguments().getString(RECIPE_COURSES);
        String recipe_cuisines = this.getArguments().getString(RECIPE_CUISINES);
        String recipe_flavors = this.getArguments().getString(RECIPE_FLAVORS);

        //Log.v(TAG, "DID COURSES COPY?: " + recipe_courses);
        //Log.v(TAG, "WHAT IS THE VALUE OF SERVINGS?: " + recipe_servings);
        Log.v(TAG, "WHAT IS THE VALUE OF TIME?: " + recipe_time);

        mTimeTaken = (TextView) view.findViewById(R.id.detail_time_taken);
        mRecipeServings = (TextView) view.findViewById(R.id.detail_servings);
        mCourses = (TextView) view.findViewById(R.id.detail_courses);
        mCuisines = (TextView) view.findViewById(R.id.detail_cuisines);
        mFlavors = (TextView) view.findViewById(R.id.detail_flavors);

        if (recipe_time != null) {
            Log.v(TAG, "THE LENGTH OF RECIPE TIME IS: " + recipe_time.length());
            Log.v(TAG, "WHY DID IT GO HERE THEN IF RECIPE TIME IS NULL?: " + recipe_time);
            String isNull = recipe_time.isEmpty() ? "YES IT'S NULL WTF" : "NO IT'S NOT NULL";
            Log.v(TAG, "Checking nullability: " + isNull);

            mTimeTaken.setVisibility(View.VISIBLE);
            mTimeTaken.setText("Time: " + recipe_time);
        }

        if (recipe_servings != null) {
            mRecipeServings.setVisibility(View.VISIBLE);
            mRecipeServings.setText("Serving(s): " + recipe_servings + " serving sizes");
        }

        Log.v(TAG, "IS THE SERVINGS ATTRIBUTE NULL?: " + recipe_servings);

        if (recipe_courses != null) {
            mCourses.setVisibility(View.VISIBLE);
            mCourses.setText("Courses: " + recipe_courses);
        }

        if (recipe_cuisines != null) {
            mCourses.setVisibility(View.VISIBLE);
            mCuisines.setText("Cuisines: " + recipe_cuisines);
        }

        if (recipe_flavors != null) {
            mFlavors.setVisibility(View.VISIBLE);
            mFlavors.setText("\nFlavors: " + recipe_flavors);
        }

        return view;
    }
}
