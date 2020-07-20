package com.technomicron.qc.ui.chat.adaptar;

import android.content.Context;
import android.graphics.Color;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.technomicron.qc.R;
import com.technomicron.qc.ui.chat.model.Chat_Model_Preview;

import java.util.ArrayList;

public class Chat_Adapter_Preview extends ArrayAdapter<Chat_Model_Preview> implements View.OnClickListener {
    Context mContext;
    // View lookup cache
    private static class ViewHolder {
        TextView channel;
        TextView message;
    }

    public Chat_Adapter_Preview(ArrayList<Chat_Model_Preview> data, Context context) {
        super(context, R.layout.item_chat_preview, data);
        this.mContext = context;

    }


    @Override
    public void onClick(View v) {
    }

    private int lastPosition = -1;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Chat_Model_Preview qcChatPreviewModel = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        Chat_Adapter_Preview.ViewHolder viewHolder; // view lookup cache stored in tag

        final View result;

        if (convertView == null) {

            viewHolder = new Chat_Adapter_Preview.ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.item_chat_preview, parent, false);
            viewHolder.channel = (TextView) convertView.findViewById(R.id.channel);
            viewHolder.message = (TextView) convertView.findViewById(R.id.message);

            result = convertView;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (Chat_Adapter_Preview.ViewHolder) convertView.getTag();
            result = convertView;
        }

        Animation animation = AnimationUtils.loadAnimation(mContext, (position > lastPosition) ? R.anim.up_from_bottom : R.anim.down_from_top);
        result.startAnimation(animation);
        lastPosition = position;

        String channelPlainText = qcChatPreviewModel.getChannel();

            if(channelPlainText.equals("#system")){
                channelPlainText = "[sys]";
            } else if(channelPlainText.equals("#organization")){
                channelPlainText = "[org]";
            } else if(channelPlainText.equals("#freelancer")){
                channelPlainText = "[fre]";
            } else if(channelPlainText.equals("#contacts")){
                channelPlainText = "[con]";
            }

        String senderPlainText = qcChatPreviewModel.getSender();
        senderPlainText += ": ";
        String messagePlainText = qcChatPreviewModel.getMessage();

        SpannableString channelSpanText = new SpannableString(channelPlainText);
        ForegroundColorSpan foregroundSpan = new ForegroundColorSpan(Color.YELLOW);
        channelSpanText.setSpan(foregroundSpan, 0, channelSpanText.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


        SpannableString senderSpanText = new SpannableString(senderPlainText);
        ForegroundColorSpan foregroundSpan2 = new ForegroundColorSpan(Color.RED);
        senderSpanText.setSpan(foregroundSpan2, 0, senderSpanText.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        SpannableString messageSpanText = new SpannableString(messagePlainText);
        viewHolder.channel.setSingleLine();
        viewHolder.channel.setText(channelSpanText);
        viewHolder.message.setSingleLine();
        viewHolder.message.setEllipsize(TextUtils.TruncateAt.END);
        viewHolder.message.setText(TextUtils.concat(senderSpanText, messageSpanText));

        // Return the completed view to render on screen
        return convertView;
    }

}
