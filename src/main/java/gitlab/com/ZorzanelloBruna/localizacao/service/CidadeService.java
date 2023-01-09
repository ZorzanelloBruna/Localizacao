package gitlab.com.ZorzanelloBruna.localizacao.service;

import gitlab.com.ZorzanelloBruna.localizacao.domain.entity.Cidade;
import gitlab.com.ZorzanelloBruna.localizacao.domain.repository.CidadeRepository;
import gitlab.com.ZorzanelloBruna.localizacao.domain.repository.specs.CidadeSpecs;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import java.util.List;

import static gitlab.com.ZorzanelloBruna.localizacao.domain.repository.specs.CidadeSpecs.*;

public class CidadeService {

    private CidadeRepository repository;

    public CidadeService(CidadeRepository repository) {
        this.repository = repository;
    }

    public void listarCidadesPorHabitantes() {
        repository.findyByHabitantesLessThan(1000L).forEach(System.out::println);
        repository.findyByHabitantesLessThanEqual(1000L).forEach(System.out::println);
        repository.findyByHabitantesGreaterThan(500L).forEach(System.out::println);
        repository.findyByHabitantes(500L).forEach(System.out::println);
    }

    public void listarCidadesPorNome() {
        Pageable pageable = PageRequest.of(1, 2);
        //o like faz a mesma coisa que byNome, ByNomeStartingWith e findByNomeEndingWith)
        repository.findByNomeLike("ch%", Sort.by("habitantes", "nome")).forEach(System.out::println);//vai buscar as cidades que contem ch ordenada pelo numero de habitantes depois por nome
        repository.findByNomeLike("%%%%", pageable).forEach(System.out::println);//vai buscar as cidades que contem ch ordenada pelo numero de habitantes depois por nome
        repository.findyByNome("Chuvisqueiro").forEach(System.out::println);
        repository.findByNomeStartingWith("Chu").forEach(System.out::println);
        repository.findByNomeEndingWith("eiro").forEach(System.out::println);
    }

    public void listarCidadesPorNomeSQL() {
        Pageable pageable = PageRequest.of(1, 2);
        //o like faz a mesma coisa que byNome, ByNomeStartingWith e findByNomeEndingWith)
        repository.findByNomeLike("ch%", Sort.by("habitantes", "nome")).forEach(System.out::println);//vai buscar as cidades que contem ch ordenada pelo numero de habitantes depois por nome
        repository.findByNomeLike("%%%%", pageable).forEach(System.out::println);//vai buscar as cidades que contem ch ordenada pelo numero de habitantes depois por nome
        repository.findyByNome("Chuvisqueiro").forEach(System.out::println);
        repository.findByNomeStartingWith("Chu").forEach(System.out::println);
        repository.findByNomeEndingWith("eiro").forEach(System.out::println);
    }

    public List<Cidade> filtroDinamico(Cidade cidade) {
        ExampleMatcher matcher = ExampleMatcher
                .matching()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.STARTING); //ignora os valores nulos e retorna os não nulos
        Example example = Example.of(cidade, matcher);
        return repository.findAll(example);
    }

    void salvarCidade() {
        var cidade = new Cidade(1L, "Chuvisqueiro", 500L);
        repository.save(cidade);
    }

    public void listarCidadesByNameSpec() {
        Specification<Cidade> spec = CidadeSpecs.nomeEqual("Chuvisqueiro");
        repository.findAll(spec).forEach(System.out::println);
    }

    void listarCidadesSpecsFiltroDinamico(Cidade filtro) {
        Specification<Cidade> specs = Specification.where((root, query, cb) -> cb.conjunction());

        //select * from cidade where 1=1

        if (filtro.getId() != null) {
            specs = specs.and(idEqual(filtro.getId()));
        }

        if (StringUtils.hasText(filtro.getNome())) {
            specs = specs.and(nomeLike(filtro.getNome()));
        }
        if (filtro.getHabitantes() != null) {
            specs = specs.and(habitantesGreaterThen(filtro.getHabitantes()));
        }

        repository.findAll(specs).forEach(System.out::println);
    }

}
