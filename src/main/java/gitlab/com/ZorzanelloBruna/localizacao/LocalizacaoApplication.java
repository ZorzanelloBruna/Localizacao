package gitlab.com.ZorzanelloBruna.localizacao;

import gitlab.com.ZorzanelloBruna.localizacao.service.CidadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

@SpringBootApplication
@Component
public class LocalizacaoApplication implements CommandLineRunner {

    @Autowired
    private CidadeService service;

    @Override
    public void run(String... args) throws Exception {
//		service.listarCidadesPorNome();
//		service.listarCidadesPorHabitantes();

//		var cidade = new Cidade(null, "chuvic", null);
//		service.filtroDinamico(cidade).forEach(System.out::println);

//        service.listarCidadesByNameSpec();
        service.listarCidadesPorNomeSQL();
    }

    public static void main(String[] args) {
        SpringApplication.run(LocalizacaoApplication.class, args);
    }
}
