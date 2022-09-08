package java.main.AndroMulti.ui.main;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.main.AndroMulti.R;
import java.main.AndroMulti.adapter.NFCReader;
import java.main.AndroMulti.utils.ReaderTask;

public class MainFragment extends Fragment {

    private static final String TAG = "MainFragment";
    private MainViewModel mViewModel;
    private ReaderTask readerTask;

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(MainViewModel.class);
        NFCReader nfcReader = new NFCReader();

        readerTask = nfcReader.init(getContext());
        Button testButton = view.findViewById(R.id.button);

        testButton.setOnClickListener(view1 -> {
            readerTask.execute();
            Log.d(TAG, "onViewCreated: DONE");
        });


    }

}