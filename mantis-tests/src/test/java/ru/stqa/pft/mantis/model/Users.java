package ru.stqa.pft.mantis.model;

import com.google.common.collect.ForwardingSet;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class Users extends ForwardingSet<UsersData> {

    private final Set<UsersData> delegate;

    public Users(Collection<UsersData> users) {
        this.delegate = new HashSet<>(users);
    }


    @Override
    protected Set delegate() {
        return delegate;
    }
}
