package gitlab.com.ZorzanelloBruna.localizacao.domain.repository;

import gitlab.com.ZorzanelloBruna.localizacao.domain.entity.Cidade;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CidadeRepository extends JpaRepository<Cidade, Long>, JpaSpecificationExecutor<Cidade> {

    @Query(nativeQuery = true, value = "select * from tb_cidade as  c where c.nome =:nome")
    List<Cidade> findyByNomeSqlNative(@Param("nome") String nome);//busca pelo nome correto

    List<Cidade> findyByNome(String nome);

    //busca pelo nome começando por n pedaço
    List<Cidade> findByNomeStartingWith(String nome);

    //busca pelo nome like ordenação
    @Query("select c from Cidade c where upper(c.nome) like upper(?1)")
    List<Cidade> findByNomeLike(String nome, Sort sort);

    //busca pelo nome like paginação
    @Query("select c from Cidade c where upper(c.nome) like upper(?1)")
    List<Cidade> findByNomeLike(String nome, Pageable pageable);

    //busca pelo nome terminando por n pedaço
    List<Cidade> findByNomeEndingWith(String nome);

    //busca pelo nome contendo n pedaço
    List<Cidade> findByNomeContaining(String nome);

    List<Cidade> findyByHabitantes(Long habitantes);

    List<Cidade> findyByHabitantesLessThan(Long habitantes);

    List<Cidade> findyByHabitantesLessThanEqual(Long habitantes);

    List<Cidade> findyByHabitantesGreaterThan(Long habitantes);

}
