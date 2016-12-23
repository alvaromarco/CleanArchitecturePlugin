#if (${PACKAGE_NAME} && ${PACKAGE_NAME} != "")package ${PACKAGE_NAME};#end

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;
import java.util.ArrayList;

import ${PACKAGE_PROJECT}.R;

import javax.inject.Inject;

#parse("File Header.java")
public class ${NAME} extends RecyclerView.Adapter<${NAME}.ViewHolder> {

    private List<Object> mElements = new ArrayList<>(); //TODO: Change your list
    
    @Inject Context mContext;

    @Inject public ${NAME}(){
    }
    
    public static class ${NAME}Holder extends RecyclerView.ViewHolder {
    
        // TODO: Bind your holder elements
        //@Bind(R.id.element) Type name;
        
        public ViewHolder(View v){
            super(v);
            //ButterKnife.bind(this, v);
        }
    }
    
    @Override public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_${LAYOUT_NAME}, parent, false);
        return new ViewHolder(v);
    }
    
    @Override public void onBindViewHolder(ViewHolder holder, int position) {
        //TODO: Complete
        //Object obj = mElements.get(position);
    }
    
     @Override public int getItemCount() {
        return mElements.size();
    }
}