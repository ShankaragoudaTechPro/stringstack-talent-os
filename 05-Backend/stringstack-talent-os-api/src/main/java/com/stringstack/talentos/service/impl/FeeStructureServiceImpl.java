package com.stringstack.talentos.service.impl;

import com.stringstack.talentos.dto.fees.FeeStructureRequest;
import com.stringstack.talentos.dto.fees.FeeStructureResponse;
import com.stringstack.talentos.entity.Course;
import com.stringstack.talentos.entity.FeeStructure;
import com.stringstack.talentos.exception.ResourceNotFoundException;
import com.stringstack.talentos.repository.CourseRepository;
import com.stringstack.talentos.repository.FeeStructureRepository;
import com.stringstack.talentos.service.FeeStructureService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class FeeStructureServiceImpl implements FeeStructureService {

    private final FeeStructureRepository feeStructureRepository;
    private final CourseRepository courseRepository;

    @Override
    public FeeStructureResponse createFeeStructure(FeeStructureRequest request) {

        Course course = courseRepository.findById(request.getCourseId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Course not found with id: " + request.getCourseId()));

        if (feeStructureRepository.existsByCourseAndActiveTrue(course)) {
            throw new IllegalStateException(
                    "An active fee structure already exists for this course");
        }

        FeeStructure feeStructure = new FeeStructure();

        feeStructure.setCourse(course);
        feeStructure.setRegistrationFee(valueOrZero(request.getRegistrationFee()));
        feeStructure.setTuitionFee(valueOrZero(request.getTuitionFee()));
        feeStructure.setMaterialFee(valueOrZero(request.getMaterialFee()));
        feeStructure.setExamFee(valueOrZero(request.getExamFee()));
        feeStructure.setDiscount(valueOrZero(request.getDiscount()));
        feeStructure.setGstPercentage(valueOrZero(request.getGstPercentage()));
        feeStructure.setEffectiveFrom(request.getEffectiveFrom());
        feeStructure.setActive(true);

        feeStructure.setTotalFee(calculateTotalFee(feeStructure));

        FeeStructure saved = feeStructureRepository.save(feeStructure);

        return mapToResponse(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public List<FeeStructureResponse> getAllFeeStructures() {

        return feeStructureRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public FeeStructureResponse getFeeStructureById(Long id) {

        FeeStructure feeStructure = feeStructureRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Fee structure not found with id: " + id));

        return mapToResponse(feeStructure);
    }

    @Override
    public FeeStructureResponse updateFeeStructure(
            Long id,
            FeeStructureRequest request) {

        FeeStructure feeStructure = feeStructureRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Fee structure not found with id: " + id));

        Course course = courseRepository.findById(request.getCourseId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Course not found with id: " + request.getCourseId()));

        feeStructure.setCourse(course);
        feeStructure.setRegistrationFee(valueOrZero(request.getRegistrationFee()));
        feeStructure.setTuitionFee(valueOrZero(request.getTuitionFee()));
        feeStructure.setMaterialFee(valueOrZero(request.getMaterialFee()));
        feeStructure.setExamFee(valueOrZero(request.getExamFee()));
        feeStructure.setDiscount(valueOrZero(request.getDiscount()));
        feeStructure.setGstPercentage(valueOrZero(request.getGstPercentage()));
        feeStructure.setEffectiveFrom(request.getEffectiveFrom());

        feeStructure.setTotalFee(calculateTotalFee(feeStructure));

        FeeStructure updated = feeStructureRepository.save(feeStructure);

        return mapToResponse(updated);
    }

    @Override
    public void deactivateFeeStructure(Long id) {

        FeeStructure feeStructure = feeStructureRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Fee structure not found with id: " + id));

        feeStructure.setActive(false);
        feeStructureRepository.save(feeStructure);
    }

    @Override
    public void activateFeeStructure(Long id) {

        FeeStructure feeStructure = feeStructureRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Fee structure not found with id: " + id));

        if (feeStructureRepository.existsByCourseAndActiveTrue(
                feeStructure.getCourse())) {

            throw new IllegalStateException(
                    "Another active fee structure already exists for this course");
        }

        feeStructure.setActive(true);
        feeStructureRepository.save(feeStructure);
    }

    private BigDecimal calculateTotalFee(FeeStructure feeStructure) {

        BigDecimal subtotal =
                feeStructure.getRegistrationFee()
                        .add(feeStructure.getTuitionFee())
                        .add(feeStructure.getMaterialFee())
                        .add(feeStructure.getExamFee())
                        .subtract(feeStructure.getDiscount());

        BigDecimal gstAmount = subtotal
                .multiply(feeStructure.getGstPercentage())
                .divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP);

        return subtotal
                .add(gstAmount)
                .setScale(2, RoundingMode.HALF_UP);
    }

    private BigDecimal valueOrZero(BigDecimal value) {
        return value == null
                ? BigDecimal.ZERO
                : value;
    }

    private FeeStructureResponse mapToResponse(
            FeeStructure feeStructure) {

        return FeeStructureResponse.builder()
                .id(feeStructure.getId())
                .courseId(feeStructure.getCourse().getId())
                .courseName(feeStructure.getCourse().getCourseName())                .registrationFee(feeStructure.getRegistrationFee())
                .tuitionFee(feeStructure.getTuitionFee())
                .materialFee(feeStructure.getMaterialFee())
                .examFee(feeStructure.getExamFee())
                .discount(feeStructure.getDiscount())
                .gstPercentage(feeStructure.getGstPercentage())
                .totalFee(feeStructure.getTotalFee())
                .active(feeStructure.getActive())
                .effectiveFrom(feeStructure.getEffectiveFrom())
                .effectiveTo(feeStructure.getEffectiveTo())
                .build();
    }
}