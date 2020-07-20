
package com.technomicron.qc.ui.chat.adaptar;

import android.content.Context;
import android.graphics.Color;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.technomicron.qc.R;
import com.technomicron.qc.ui.chat.model.Chat_Model;

import java.util.ArrayList;

    public class Chat_Adapter extends ArrayAdapter<Chat_Model> implements View.OnClickListener{
        Context mContext;

        // View lookup cache
        private static class ViewHolder {
            TextView channel;
            TextView message;
        }

        public Chat_Adapter(ArrayList<Chat_Model> data, Context context) {
            super(context, R.layout.item_chat_system, data);
            this.mContext = context;

        }

        @Override
        public void onClick(View v) {
        }

        private int lastPosition = -1;

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // Get the data item for this position
            Chat_Model qcChatModel = getItem(position);
            // Check if an existing view is being reused, otherwise inflate the view
            ViewHolder viewHolder; // view lookup cache stored in tag

            final View result;

            if (convertView == null) {

                viewHolder = new ViewHolder();
                LayoutInflater inflater = LayoutInflater.from(getContext());
                convertView = inflater.inflate(R.layout.item_chat_system, parent, false);
                viewHolder.channel = (TextView) convertView.findViewById(R.id.channel);
                viewHolder.message = (TextView) convertView.findViewById(R.id.message);

                result=convertView;

                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
                result=convertView;
            }

            Animation animation = AnimationUtils.loadAnimation(mContext, (position > lastPosition) ? R.anim.up_from_bottom : R.anim.down_from_top);
            result.startAnimation(animation);
            lastPosition = position;
            String senderPlainText = qcChatModel.getSender();
            String messagePlainText = qcChatModel.getMessage();



            SpannableString senderSpanText = new SpannableString(senderPlainText);
            ForegroundColorSpan foregroundSpan2 = new ForegroundColorSpan(Color.WHITE);
            senderSpanText.setSpan(foregroundSpan2, 0, senderSpanText.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

            SpannableString messageSpanText = new SpannableString(messagePlainText);

            viewHolder.channel.setText(senderSpanText);
            viewHolder.message.setText(messageSpanText);
            // Return the completed view to render on screen
            return convertView;
        }
    }
