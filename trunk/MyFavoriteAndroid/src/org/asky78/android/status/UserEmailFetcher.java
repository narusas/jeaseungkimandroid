package org.asky78.android.status;

import android.accounts.*;
import android.content.*;

public class UserEmailFetcher {
	public static String getEmail(Context context) {
		AccountManager accountManager = AccountManager.get(context);
		Account account = getAccount(accountManager);

		if (account == null) {
			return null;
		} else {
			return account.name;
		}
	}

	private static Account getAccount(AccountManager accountManager) {
		Account[] accounts = accountManager.getAccountsByType("com.google");
		Account account;
		if (accounts.length > 0) {
			account = accounts[0];
		} else {
			account = null;
		}
		return account;
	}
}
