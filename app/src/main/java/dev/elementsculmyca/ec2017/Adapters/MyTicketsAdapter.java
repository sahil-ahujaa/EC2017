package dev.elementsculmyca.ec2017.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import net.glxn.qrgen.android.QRCode;

import java.util.ArrayList;

import dev.elementsculmyca.ec2017.BitmapHandlers.BItmapHelper;
import dev.elementsculmyca.ec2017.R;
import dev.elementsculmyca.ec2017.Utility.TicketDetails;

/**
 * Created by hemba on 2/16/2017.
 */

public class MyTicketsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    ArrayList<TicketDetails> qrCodeList;
    Context context;

    public MyTicketsAdapter(ArrayList<TicketDetails> qrCodeList, Context context) {
        this.qrCodeList = qrCodeList;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new TicketHolder(LayoutInflater.from(parent.getContext()).inflate( R.layout.ticket_qr_layout,parent,false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            ((TicketHolder) holder).qrCode.setImageBitmap(QRCode.from(qrCodeList.get(position).getQrCode()).withSize(280,280).bitmap());
            ((TicketHolder) holder).eventName.setText(qrCodeList.get(position).getEventName());
    }

    @Override
    public int getItemCount() {
        return qrCodeList.size();
    }

    protected class TicketHolder extends RecyclerView.ViewHolder{
        ImageView qrCode;
        TextView eventName;
        public TicketHolder(View itemView) {
            super(itemView);
            qrCode=(ImageView)itemView.findViewById(R.id.ticket_image_view);
            eventName=(TextView)itemView.findViewById(R.id.ticket_event_name);
        }
    }


}
