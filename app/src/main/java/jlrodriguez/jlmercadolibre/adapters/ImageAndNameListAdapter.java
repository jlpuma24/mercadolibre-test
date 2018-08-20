package jlrodriguez.jlmercadolibre.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;
import jlrodriguez.jlmercadolibre.R;
import jlrodriguez.jlmercadolibre.holders.RowImageViewHolder;
import jlrodriguez.jlmercadolibre.model.Issuer;
public class ImageAndNameListAdapter extends RecyclerView.Adapter<RowImageViewHolder> {

    private List<? extends Issuer> dataList;
    private Context mContext;

    public ImageAndNameListAdapter(List<? extends Issuer> dataList, Context mContext) {
        this.dataList = dataList;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public RowImageViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.row_credit_card, viewGroup, false);
        return new RowImageViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final RowImageViewHolder creditCardHolder, int i) {
        Issuer issuerObject = dataList.get(i);
        creditCardHolder.tvCardName.setText(issuerObject.getName());
        if (issuerObject.getThumbnail() != null && !issuerObject.getThumbnail().isEmpty()) {
            Picasso.get()
                    .load(issuerObject.getThumbnail())
                    .into(creditCardHolder.ivCardImage, new Callback() {
                        @Override
                        public void onSuccess() {
                            creditCardHolder.pbLoaderImage.setVisibility(View.GONE);
                        }

                        @Override
                        public void onError(Exception e) {
                            creditCardHolder.pbLoaderImage.setVisibility(View.GONE);
                        }
                    });
        } else {
            creditCardHolder.pbLoaderImage.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }
}