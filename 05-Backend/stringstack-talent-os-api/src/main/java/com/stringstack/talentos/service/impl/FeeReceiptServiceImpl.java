package com.stringstack.talentos.service.impl;

import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import com.stringstack.talentos.dto.fees.FeeReceiptResponse;
import com.stringstack.talentos.entity.FeePayment;
import com.stringstack.talentos.exception.ResourceNotFoundException;
import com.stringstack.talentos.repository.FeePaymentRepository;
import com.stringstack.talentos.service.FeeReceiptService;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.awt.Color;
import java.io.ByteArrayOutputStream;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class FeeReceiptServiceImpl
        implements FeeReceiptService {

    private final FeePaymentRepository feePaymentRepository;

    @Override
    public FeeReceiptResponse getReceiptDetails(Long paymentId) {

        FeePayment payment =
                feePaymentRepository.findById(paymentId)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Payment not found with id: "
                                                + paymentId
                                ));

        return buildReceiptResponse(payment);
    }

    @Override
    public byte[] generateReceiptPdf(Long paymentId) {

        FeePayment payment =
                feePaymentRepository.findById(paymentId)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Payment not found with id: "
                                                + paymentId
                                ));

        FeeReceiptResponse receipt =
                buildReceiptResponse(payment);

        try {

            ByteArrayOutputStream outputStream =
                    new ByteArrayOutputStream();

            Document document =
                    new Document(
                            PageSize.A4,
                            40,
                            40,
                            40,
                            40
                    );

            PdfWriter.getInstance(
                    document,
                    outputStream
            );

            document.open();

            // =========================
            // INSTITUTE HEADER
            // =========================

            Font instituteFont =
                    new Font(
                            Font.HELVETICA,
                            18,
                            Font.BOLD
                    );

            Paragraph instituteName =
                    new Paragraph(
                            receipt.getInstituteName(),
                            instituteFont
                    );

            instituteName.setAlignment(
                    Element.ALIGN_CENTER
            );

            document.add(instituteName);

            Font normalFont =
                    new Font(
                            Font.HELVETICA,
                            9
                    );

            Paragraph instituteDetails =
                    new Paragraph(
                            receipt.getInstituteAddress()
                                    + "\nPhone: "
                                    + receipt.getInstitutePhone()
                                    + " | Email: "
                                    + receipt.getInstituteEmail(),
                            normalFont
                    );

            instituteDetails.setAlignment(
                    Element.ALIGN_CENTER
            );

            document.add(instituteDetails);

            document.add(
                    new Paragraph(" ")
            );

            // =========================
            // RECEIPT TITLE
            // =========================

            Font receiptHeadingFont =
                    new Font(
                            Font.HELVETICA,
                            16,
                            Font.BOLD
                    );

            Paragraph receiptHeading =
                    new Paragraph(
                            "FEE PAYMENT RECEIPT",
                            receiptHeadingFont
                    );

            receiptHeading.setAlignment(
                    Element.ALIGN_CENTER
            );

            document.add(receiptHeading);

            document.add(
                    new Paragraph(" ")
            );

            // =========================
            // RECEIPT INFORMATION
            // =========================

            PdfPTable receiptInfo =
                    new PdfPTable(2);

            receiptInfo.setWidthPercentage(100);

            addCell(
                    receiptInfo,
                    "Receipt Number",
                    receipt.getReceiptNumber()
            );

            addCell(
                    receiptInfo,
                    "Payment Date",
                    receipt.getPaymentDate() != null
                            ? receipt.getPaymentDate().toString()
                            : "-"
            );

            document.add(receiptInfo);

            document.add(
                    new Paragraph(" ")
            );

            // =========================
            // STUDENT DETAILS
            // =========================

            Font sectionFont =
                    new Font(
                            Font.HELVETICA,
                            11,
                            Font.BOLD
                    );

            document.add(
                    new Paragraph(
                            "Student Details",
                            sectionFont
                    )
            );

            PdfPTable studentTable =
                    new PdfPTable(2);

            studentTable.setWidthPercentage(100);

            addCell(
                    studentTable,
                    "Student Name",
                    receipt.getStudentName()
            );

            addCell(
                    studentTable,
                    "Student Code",
                    receipt.getStudentCode()
            );

            addCell(
                    studentTable,
                    "Course",
                    receipt.getCourseName()
            );

            addCell(
                    studentTable,
                    "Batch",
                    receipt.getBatchName()
            );

            document.add(studentTable);

            document.add(
                    new Paragraph(" ")
            );

            // =========================
            // PAYMENT DETAILS
            // =========================

            document.add(
                    new Paragraph(
                            "Payment Details",
                            sectionFont
                    )
            );

            PdfPTable paymentTable =
                    new PdfPTable(2);

            paymentTable.setWidthPercentage(100);

            addCell(
                    paymentTable,
                    "Installment Number",
                    receipt.getInstallmentNumber() != null
                            ? receipt.getInstallmentNumber().toString()
                            : "-"
            );

            addCell(
                    paymentTable,
                    "Payment Mode",
                    receipt.getPaymentMode()
            );

            addCell(
                    paymentTable,
                    "Transaction Reference",
                    receipt.getTransactionReference()
            );

            addCell(
                    paymentTable,
                    "Amount Paid",
                    "₹ " + receipt.getAmountPaid()
            );

            addCell(
                    paymentTable,
                    "Total Fee",
                    "₹ " + receipt.getTotalFee()
            );

            addCell(
                    paymentTable,
                    "Total Paid",
                    "₹ " + receipt.getTotalPaid()
            );

            addCell(
                    paymentTable,
                    "Pending Amount",
                    "₹ " + receipt.getPendingAmount()
            );

            document.add(paymentTable);

            document.add(
                    new Paragraph(" ")
            );

            // =========================
            // REMARKS
            // =========================

            addCellParagraph(
                    document,
                    "Remarks",
                    receipt.getRemarks()
            );

            document.add(
                    new Paragraph(" ")
            );

            // =========================
            // SIGNATURE
            // =========================

            PdfPTable signatureTable =
                    new PdfPTable(2);

            signatureTable.setWidthPercentage(100);

            PdfPCell studentSignature =
                    new PdfPCell(
                            new Phrase(
                                    "\n\nStudent Signature"
                            )
                    );

            PdfPCell authorizedSignature =
                    new PdfPCell(
                            new Phrase(
                                    "\n\nAuthorized Signature"
                            )
                    );

            studentSignature.setBorder(
                    PdfPCell.NO_BORDER
            );

            authorizedSignature.setBorder(
                    PdfPCell.NO_BORDER
            );

            studentSignature.setHorizontalAlignment(
                    Element.ALIGN_LEFT
            );

            authorizedSignature.setHorizontalAlignment(
                    Element.ALIGN_RIGHT
            );

            signatureTable.addCell(
                    studentSignature
            );

            signatureTable.addCell(
                    authorizedSignature
            );

            document.add(signatureTable);

            document.add(
                    new Paragraph(" ")
            );

            // =========================
            // FOOTER
            // =========================

            Paragraph footer =
                    new Paragraph(
                            "This is a computer-generated fee receipt.",
                            normalFont
                    );

            footer.setAlignment(
                    Element.ALIGN_CENTER
            );

            document.add(footer);

            document.close();

            return outputStream.toByteArray();

        } catch (Exception e) {

            throw new IllegalStateException(
                    "Unable to generate fee receipt PDF",
                    e
            );
        }
    }

    // ==========================================
    // BUILD RECEIPT RESPONSE
    // ==========================================

    private FeeReceiptResponse buildReceiptResponse(
            FeePayment payment) {

        var studentFee =
                payment.getStudentFee();

        var enrollment =
                studentFee.getEnrollment();

        var student =
                enrollment.getStudent();

        var feeStructure =
                studentFee.getFeeStructure();

        var course =
                feeStructure.getCourse();

        return FeeReceiptResponse.builder()

                .paymentId(
                        payment.getId()
                )

                .receiptNumber(
                        payment.getReceiptNumber()
                )

                .paymentDate(
                        payment.getPaymentDate()
                )

                .instituteName(
                        "StringStack Training & Placement Center"
                )

                .instituteAddress(
                        "Bengaluru, Karnataka"
                )

                .institutePhone(
                        "Institute Contact Number"
                )

                .instituteEmail(
                        "institute@email.com"
                )

                .studentName(
                        student.getFirstName()
                                + " "
                                + student.getLastName()
                )

                .studentCode(
                        student.getStudentCode()
                )

                .courseName(
                        course.getCourseName()
                )

                /*
                 * Batch is temporarily kept as "-".
                 * Later we will connect your actual Batch entity.
                 */
                .batchName(
                        "-"
                )

                .installmentNumber(
                        payment.getInstallment()
                                .getInstallmentNumber()
                )

                .totalFee(
                        studentFee.getFinalFee()
                )

                .amountPaid(
                        payment.getAmount()
                )

                .totalPaid(
                        studentFee.getPaidAmount()
                )

                .pendingAmount(
                        studentFee.getPendingAmount()
                )

                .paymentMode(
                        payment.getPaymentMode().name()
                )

                .transactionReference(
                        payment.getTransactionReference()
                )

                .remarks(
                        payment.getRemarks()
                )

                .build();
    }

    // ==========================================
    // PDF TABLE CELL
    // ==========================================

    private void addCell(
            PdfPTable table,
            String label,
            String value) {

        PdfPCell labelCell =
                new PdfPCell(
                        new Phrase(label)
                );

        PdfPCell valueCell =
                new PdfPCell(
                        new Phrase(
                                value == null
                                        ? "-"
                                        : value
                        )
                );

        labelCell.setBackgroundColor(
                Color.LIGHT_GRAY
        );

        table.addCell(labelCell);
        table.addCell(valueCell);
    }

    // ==========================================
    // PDF PARAGRAPH
    // ==========================================

    private void addCellParagraph(
            Document document,
            String label,
            String value) {

        document.add(
                new Paragraph(
                        label
                                + ": "
                                + (
                                value == null
                                        ? "-"
                                        : value
                        )
                )
        );
    }
}