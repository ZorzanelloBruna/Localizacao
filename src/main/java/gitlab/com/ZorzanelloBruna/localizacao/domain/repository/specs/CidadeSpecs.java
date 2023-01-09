package gitlab.com.ZorzanelloBruna.localizacao.domain.repository.specs;

import gitlab.com.ZorzanelloBruna.localizacao.domain.entity.Cidade;
import org.springframework.data.jpa.domain.Specification;

public class CidadeSpecs {
    public static Specification<Cidade> idEqual(Long id) {
        return (root, query, cb) -> cb.equal(root.get("id"), id);
    }

    public static Specification<Cidade> nomeEqual(String nome) {
        return (root, query, cb) -> cb.equal(root.get("nome"), nome);
    }

    public static Specification<Cidade> habitantesGreaterThen(Long value) {
        return (root, query, cb) -> cb.greaterThan(root.get("habitantes"), value);
    }

    public static Specification<Cidade> habitantesBetween(Long min, Long max) {
        return (root, query, cb) -> cb.between(root.get("habitantes"), min, max);
    }

    public static Specification<Cidade> nomeLike(String nome) {
        return (root, query, cb) -> cb.like(cb.upper(root.get("nome")), "%" + nome + "%".toUpperCase());
    }


}
