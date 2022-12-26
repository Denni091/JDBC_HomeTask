package hillel.ua;

import hillel.ua.entity.Account;
import hillel.ua.entity.Clients;
import hillel.ua.entity.Status;
import hillel.ua.service.AccountService;
import hillel.ua.service.ClientService;
import hillel.ua.service.StatusService;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        ClientService clientService = new ClientService();
        List<Clients> clientsGetAll = clientService.getAll();
        clientsGetAll.forEach(System.out::println);
        List<Clients> searchPhone = clientService.searchPhone();
        searchPhone.forEach(System.out::println);
        List<Clients> getClients = clientService.getClients();
        getClients.forEach(System.out::println);
        List<Clients> getAllTables = clientService.allTable();
        getAllTables.forEach(System.out::println);

        Clients clients = new Clients();
        clients.setName("Polina");
        clients.setEmail("Porch");
        clients.setPhone(972362782);
        clients.setAbout("Pretty girl");
        clients.setAge("19");
        clientService.save(clients);

        clientService.update(clients);
        clientService.delete(clients);


        StatusService statusService = new StatusService();
        List<Status> statusGetAll = statusService.getAll();
        statusGetAll.forEach(System.out::println);


        Status status = new Status();
        status.setAlias("Admin");
        status.setDescription("The main in the institution");
        statusService.save(status);
        statusService.update(status);
        statusService.delete(status);

        AccountService accountService = new AccountService();
        List<Account> accountGetAll = accountService.getAll();
        accountGetAll.forEach(System.out::println);
        List<Account> getNumber = accountService.getNumber();
        getNumber.forEach(System.out::println);
        Account account = new Account();
        account.setClient_id(2);
        account.setNumber("11");
        account.setValue(22435);
        accountService.save(account);
        accountService.update(account);
        accountService.delete(account);
    }
}
