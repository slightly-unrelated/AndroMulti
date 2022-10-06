package java.main.AndroMulti.ui.main;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;

import java.main.AndroMulti.R;
import java.main.AndroMulti.adapter.NFCReader;
import java.main.AndroMulti.adapter.RecyclerAdapter;
import java.main.AndroMulti.utils.CSVReaderWriter;
import java.main.AndroMulti.utils.ReaderTask;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainFragment extends Fragment {

    private static final String TAG = "MainFragment";
    private MainViewModel mViewModel;
    private ReaderTask readerTask;
    private Context mContext;
    private Button addIDButton;
    private TextInputLayout IDTextbox;
    private RecyclerView recyclerView;
    private RecyclerAdapter recyclerAdapter;
    private List<String> additionalHeadersList;


    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mContext = container.getContext();
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(MainViewModel.class);
        NFCReader nfcReader = new NFCReader();
        addIDButton = view.findViewById(R.id.addIDButton);
        IDTextbox = view.findViewById(R.id.IDTextbox);

        readerTask = nfcReader.init(getContext());

        additionalHeadersList = new ArrayList<>();

        additionalHeadersList.add("test option");
        additionalHeadersList.add("test option");
        additionalHeadersList.add("test option");
        additionalHeadersList.add("test option");
        additionalHeadersList.add("test option");
        additionalHeadersList.add("test option");
        additionalHeadersList.add("test option");
        additionalHeadersList.add("test option");
        additionalHeadersList.add("test option");
        additionalHeadersList.add("test option");
        additionalHeadersList.add("test option");
        additionalHeadersList.add("test option");
        additionalHeadersList.add("test option");
        additionalHeadersList.add("test option");
        additionalHeadersList.add("test option");


        recyclerView = view.findViewById(R.id.recyclerViewMain);
        recyclerAdapter = new RecyclerAdapter(additionalHeadersList);
        recyclerView.setAdapter(recyclerAdapter);

        recyclerView.addItemDecoration(
                new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL));

        new ItemTouchHelper(simpleCallback)
                .attachToRecyclerView(recyclerView);

        addIDButton.setOnClickListener(view1 -> {

            CSVReaderWriter testRW = new CSVReaderWriter();
            testRW.writeToCSV(mContext, SharedPrefIO.getStoredCardID(mContext) + ",", true);

            String message = "" +
                    SharedPrefIO.getStoredCardID(mContext);
            Log.d(TAG, message);

            Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
        });
    }

    public void onViewCallback() {
        IDTextbox.getEditText().setText(SharedPrefIO.getStoredCardID(mContext));
    }

    ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP | ItemTouchHelper.DOWN | ItemTouchHelper.START | ItemTouchHelper.END, 0) {

        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            int fromPosition = viewHolder.getAdapterPosition();
            int toPosition = target.getAdapterPosition();
            Collections.swap(additionalHeadersList, fromPosition, toPosition);
            recyclerView.getAdapter().notifyItemMoved(fromPosition, toPosition);
            return false;
        }

        String deletedItem;

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            final int position = viewHolder.getAdapterPosition();

            switch (direction) {
                case ItemTouchHelper.LEFT:
                    deletedItem = additionalHeadersList.get(position);
                    additionalHeadersList.remove(position);
                    recyclerAdapter.notifyItemRemoved(position);
                    Snackbar.make(recyclerView, deletedItem, Snackbar.LENGTH_LONG)
                            .setAction("Undo", new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    additionalHeadersList.add(position, deletedItem);
                                    recyclerAdapter.notifyItemInserted(position);
                                }
                            }).show();
                    break;
            }
        }
    };
}