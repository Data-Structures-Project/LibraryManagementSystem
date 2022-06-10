package librarymanagementsystem.dao;

import librarymanagementsystem.model.Account;
import utility.BinarySearchTree;

import java.util.List;

public class AccountRepositoryImpl implements AccountRepository{
    private BinarySearchTree<Account> accounts;

    public AccountRepositoryImpl(){
        accounts = new BinarySearchTree<>();
    }

    @Override
    public List<Account> findAll() {
        return null;
    }

    @Override
    public Account findById(Long id) {
        return null;
    }

    @Override
    public List<Account> findBySurName(String surname) {
        return null;
    }

    @Override
    public void create(Account account) {

    }

    @Override
    public Account update(Account account) {
        return null;
    }

    @Override
    public void remove(Account account) {

    }

    @Override
    public String viewInfo(Long id) {
        return null;
    }
}
