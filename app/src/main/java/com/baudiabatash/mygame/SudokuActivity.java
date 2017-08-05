package com.baudiabatash.mygame;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;

import com.baudiabatash.mygame.Layout.ControlView;
import com.baudiabatash.mygame.Layout.SudokuView;
import com.baudiabatash.mygame.Listener.MyListener;
import com.baudiabatash.mygame.Model.Control;

public class SudokuActivity extends AppCompatActivity implements MyListener {
    private SudokuView sudokuView;
    private ControlView controlView;

    private FrameLayout frameLayout,controllerContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sudokuView = new SudokuView(this);
        controlView = new ControlView(this);
        controlView.setListener(this);
        setContentView(R.layout.activity_sudoku);

        frameLayout = (FrameLayout) findViewById(R.id.sudoku_container);
        controllerContainer = (FrameLayout) findViewById(R.id.controller_container);
        frameLayout.addView(sudokuView);
        controllerContainer.addView(controlView);
    }

    @Override
    public void onClick(Control control) {
        Log.d("HHH",control.getValue());

        sudokuView.setValue(control.getValue());
    }
}
