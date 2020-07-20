package com.technomicron.qc.service;

import android.os.Binder;

import com.technomicron.qc.service.QCService;

public class QCService_Binder extends Binder {
    private final QCService service;

    /**
     * Create a new binder for given service
     *
     * @param service
     */
    public QCService_Binder(QCService service)
    {
        super();

        this.service = service;
    }

    /**
     * Get service associated with this binder
     *
     * @return
     */
    public QCService getService()
    {
        return service;
    }
}
