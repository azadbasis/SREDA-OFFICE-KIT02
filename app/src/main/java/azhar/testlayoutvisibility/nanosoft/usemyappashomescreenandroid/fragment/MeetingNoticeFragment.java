package azhar.testlayoutvisibility.nanosoft.usemyappashomescreenandroid.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import azhar.testlayoutvisibility.nanosoft.usemyappashomescreenandroid.R;
import azhar.testlayoutvisibility.nanosoft.usemyappashomescreenandroid.adapter.OffecialBeanAdapter;
import azhar.testlayoutvisibility.nanosoft.usemyappashomescreenandroid.model.OfficialBean;

/**
 * A simple {@link Fragment} subclass.
 */
public class MeetingNoticeFragment extends Fragment {


    public MeetingNoticeFragment() {
        // Required empty public constructor
    }

    private RecyclerView recyclerViewOffecial;
    private ArrayList<OfficialBean> officialBeanArrayList;
    OffecialBeanAdapter offecialBeanAdapter;
    protected RecyclerView.LayoutManager mLayoutManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.meeting_notice_fragment, container, false);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        intView(view);
    }


    private void intView(View view) {

        officialBeanArrayList = new ArrayList<>();
        recyclerViewOffecial = (RecyclerView) view.findViewById(R.id.recyclerViewOffecial);
        mLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerViewOffecial.setLayoutManager(mLayoutManager);
        offecialBeanAdapter = new OffecialBeanAdapter(getActivity(), officialBeanArrayList);
        recyclerViewOffecial.setAdapter(offecialBeanAdapter);

        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(getActivity());
        String url = "http://192.168.0.109/sreda_api/users/";

// Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        JSONArray jsonArray = null;
                        try {
                            jsonArray = new JSONArray(response);
                            for (int i = 0; i >= jsonArray.length(); i++) {
                                JSONObject jresponse = jsonArray.getJSONObject(i);
                                String name = jresponse.getString("employee_name");
                                Toast.makeText(getContext(), "Employee Name"+name, Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                        // Display the first 500 characters of the response string.
                        //mTextView.setText("Response is: "+ response.substring(0,500));
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //     mTextView.setText("That didn't work!");
            }
        });
// Add the request to the RequestQueue.
        queue.add(stringRequest);

    }
}
