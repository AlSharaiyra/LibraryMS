package org.LibraryMS.service;

import org.LibraryMS.model.Book;
import org.LibraryMS.model.Member;
import org.LibraryMS.repository.MemberRepository;
import org.LibraryMS.util.ValidationUtil;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class MemberService {
    private final MemberRepository memberRepo;
    private final ValidationUtil validationUtil;

    public MemberService(MemberRepository memberRepo, ValidationUtil validationUtil) {
        this.memberRepo = memberRepo;
        this.validationUtil = validationUtil;
    }

    public void registerMember(String name, String email, String phoneNumber) {
        LocalDateTime currentDateTime = LocalDateTime.now();
        Timestamp timestamp = Timestamp.valueOf(currentDateTime);

        Member member = new Member(name, email, phoneNumber, timestamp);

        if (memberRepo.isEmailOrPhoneNumberTaken(member.getEmail(), member.getPhoneNumber())) {
            throw new IllegalArgumentException("Email or phone number already in use.");
        }
        if (!validationUtil.isEmailValid(member.getEmail())){
            throw new IllegalArgumentException("Email is not valid. Please try again.");
        }
        if (!validationUtil.isPhoneNumberValid(member.getPhoneNumber())){
            throw new IllegalArgumentException("Phone number is not valid. Please try again.");
        }
        memberRepo.save(member);
    }

    public Member getMemberById(int memberId) {
        Member member = memberRepo.findById(memberId);
        if (member == null) {
            throw new IllegalArgumentException("No member found with ID: " + memberId);
        }

        return member;
    }

    // Not used for now
    public List<Member> listAllMembers() {
        return memberRepo.findAll();
    }

    public void deleteMember(int memberId) {
        Member member = memberRepo.findById(memberId);
        if (member == null) {
            throw new IllegalArgumentException("Cannot delete. No member found with ID: " + memberId);
        }
        memberRepo.deleteById(memberId);
    }

    public List<Book> findBorrowedBooksByMemberId(int memberId) {
        Member member = memberRepo.findById(memberId);
        if (member == null){
            throw new IllegalArgumentException("No member found with ID: " + memberId);
        }
        return memberRepo.findBorrowedBooksByMemberId(memberId);
    }

    public void updateMember(Member member) {
//        Member existingMember = memberRepo.findById(member.getId());
//
//        // todo: this need correction (fails when using the same email or phone number)
//        if (!existingMember.getEmail().equals(member.getEmail()) || !existingMember.getPhoneNumber().equals(member.getPhoneNumber())) {
//            if (memberRepo.isEmailOrPhoneNumberTaken(member.getEmail(), member.getPhoneNumber())) {
//                throw new IllegalArgumentException("Email or phone number already in use.");
//            }
//        }
        if (!validationUtil.isEmailValid(member.getEmail())){
            throw new IllegalArgumentException("Email is not valid. Please try again.");
        }
        if (!validationUtil.isPhoneNumberValid(member.getPhoneNumber())){
            throw new IllegalArgumentException("Phone number is not valid. Please try again.");
        }

        memberRepo.update(member);
    }

}

// 5 features: add member + update member + remove member + find member by ID + list all books borrowed by a member
// todo: search member by name