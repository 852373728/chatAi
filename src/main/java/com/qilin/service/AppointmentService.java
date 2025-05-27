package com.qilin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qilin.entity.Appointment;

public interface AppointmentService extends IService<Appointment> {

    Appointment getOne(Appointment appointment);

}
