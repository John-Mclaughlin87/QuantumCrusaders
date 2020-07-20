
package com.technomicron.qc.ui.chat.model;

import androidx.lifecycle.ViewModel;


public class Chat_Model extends ViewModel {

    String channel;
    String message;
    String sender;

        public Chat_Model(String channel, String message, String sender) {
            this.channel = channel;
            this.message = message;
            this.sender = sender;
        }

        public String getChannel() { return channel; }
        public String getSender() { return sender; }
        public String getMessage() {
            return message;
        }

}

