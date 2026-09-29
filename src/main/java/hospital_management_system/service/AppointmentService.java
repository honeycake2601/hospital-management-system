package hospital_management_system.service;

import hospital_management_system.entity.Appointment;
import hospital_management_system.repository.AppointmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;

    public AppointmentService(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    public Appointment addAppointment(Appointment appointment) {
        return appointmentRepository.save(appointment);
    }

    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    public Optional<Appointment> getAppointmentById(Long id) {
        return appointmentRepository.findById(id);
    }

    public Appointment updateAppointment(Long id, Appointment updatedAppointment) {
        return appointmentRepository.findById(id)
                .map(appointment -> {
                    appointment.setPatientId(updatedAppointment.getPatientId());
                    appointment.setDoctorId(updatedAppointment.getDoctorId());
                    appointment.setAppointmentDate(updatedAppointment.getAppointmentDate());
                    appointment.setAppointmentTime(updatedAppointment.getAppointmentTime());
                    appointment.setReason(updatedAppointment.getReason());

                    return appointmentRepository.save(appointment);
                })
                .orElse(null);
    }

    public boolean deleteAppointment(Long id) {
        if (appointmentRepository.existsById(id)) {
            appointmentRepository.deleteById(id);
            return true;
        }

        return false;
    }
}