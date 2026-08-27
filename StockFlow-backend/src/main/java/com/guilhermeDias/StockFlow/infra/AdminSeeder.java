package com.guilhermeDias.StockFlow.infra;

import com.guilhermeDias.StockFlow.entity.Empresa;
import com.guilhermeDias.StockFlow.entity.UserRole;
import com.guilhermeDias.StockFlow.entity.Usuario;
import com.guilhermeDias.StockFlow.repository.EmpresaRepository;
import com.guilhermeDias.StockFlow.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class AdminSeeder implements CommandLineRunner {

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private EmpresaRepository empresaRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Value("${admin.seed.email}")
    private String adminEmail;

    @Value("${admin.seed.senha}")
    private String adminSenha;

    @Value("${admin.seed.cpf}")
    private String adminCpf;

    @Override
    public void run(String... args) throws Exception {
        if(repository.existsByEmail(adminEmail)) {
            return;
        }

        Empresa empresaAdmin = empresaRepository.findByCnpj("00000000000000")
                .orElseGet(() -> {
                    Empresa empresa = new Empresa();
                    empresa.setNome("StockFlow - Admin");
                    empresa.setCnpj("00000000000000");
                    empresa.setEmail("admin@stockflow.com");
                    return empresaRepository.save(empresa);
                });
        Usuario admin = new Usuario();
        admin.setNome("Administrador");
        admin.setCpf(adminCpf);
        admin.setEmail(adminEmail);
        admin.setSenha(passwordEncoder.encode(adminSenha));
        admin.setRole(UserRole.ADMIN);
        admin.setAtivo(true);
        admin.setEmpresa(empresaAdmin);

        repository.save(admin);
    }
}
