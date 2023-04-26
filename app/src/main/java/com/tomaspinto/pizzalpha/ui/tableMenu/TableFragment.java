package com.tomaspinto.pizzalpha.ui.tableMenu;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.tomaspinto.pizzalpha.Data.AppDatabase;
import com.tomaspinto.pizzalpha.Data.AppRepository;
import com.tomaspinto.pizzalpha.Data.Table;
import com.tomaspinto.pizzalpha.R;
import com.tomaspinto.pizzalpha.databinding.FragmentTableBinding;
import com.tomaspinto.pizzalpha.menu_waiter;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class TableFragment extends Fragment {

    private FragmentTableBinding binding;
    private AppRepository db;
    private View v;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        TableViewModel homeViewModel =
                new ViewModelProvider(this).get(TableViewModel.class);

        binding = FragmentTableBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        v = binding.getRoot();

        db = new AppRepository(AppDatabase.getDatabase(getContext()));

        createTables();

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public void createTables()
    {
        // Setting Layout for menu
        ScrollView scrollView = v.findViewById(R.id.scroll);
        LinearLayout layout = new LinearLayout(getContext());
        layout.setOrientation(LinearLayout.VERTICAL);
        scrollView.addView(layout);

        Queue<Button> q = createTableButtons();
        createGrid(q, layout);
    }

    public Queue<Button> createTableButtons()
    {
        Queue<Button> buttonList = new LinkedList<Button>();
        List<Table> tableList = db.getTableList();

        for(Table table : tableList)
        {
            // Create Button Dynamically
            Button btn = new Button(getContext());
            btn.setText(table.tableName);
            btn.setTextSize(20);
            btn.setTypeface(null, Typeface.BOLD);
            btn.setTextColor(getResources().getColor(R.color.white));
            btn.setBackgroundTintList(getActivity().getColorStateList(R.color.orange));
            btn.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));

            final float scale = getActivity().getApplicationContext().getResources().getDisplayMetrics().density;
            btn.setWidth((int)(115 * scale));
            btn.setHeight((int)(115 * scale));

            btn.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(), menu_waiter.class);
                    intent.putExtra("table", table);
                    startActivity(intent);
                }
            });

            buttonList.add(btn);
        }
        return buttonList;
    }

    public void createGrid(Queue<Button> q, LinearLayout layout)
    {
        int numRows = q.size()/3 + q.size()%3;
        for(int i = numRows; i > 0; i--)
        {
            LinearLayout row = new LinearLayout(getContext());
            row.setLayoutParams(new LinearLayout.LayoutParams
                    (LinearLayout.LayoutParams.WRAP_CONTENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT));
            for(int j = 1; j <=3; j++)
            {
                Button btn = q.peek();
                row.addView(btn);
                q.remove();
            }

            layout.addView(row);
        }
    }

}