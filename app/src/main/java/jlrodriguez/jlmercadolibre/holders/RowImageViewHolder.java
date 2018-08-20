package jlrodriguez.jlmercadolibre.holders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import jlrodriguez.jlmercadolibre.R;

public class RowImageViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.pb_loaderImage)
    public ProgressBar pbLoaderImage;
    @BindView(R.id.iv_cardImage)
    public ImageView ivCardImage;
    @BindView(R.id.tv_cardName)
    public TextView tvCardName;

    public RowImageViewHolder(View v) {
        super(v);
        ButterKnife.bind(this, v);
    }
}
