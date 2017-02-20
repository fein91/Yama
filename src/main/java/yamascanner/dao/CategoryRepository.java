package yamascanner.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import yamascanner.rest.dto.Category;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Repository
public class CategoryRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Transactional(readOnly = true)
    public List<Category> findAll() {
        return jdbcTemplate.query("select * from category_tmp",
                new CategoryRowMapper());
    }

    @Transactional(readOnly = true)
    public Category findById(long id) {
        return jdbcTemplate.queryForObject("select * from category_tmp where id = ?",
                new Object[] { id },
                new CategoryRowMapper());
    }

    @Transactional
    public void create(Category category) {
        String sql = "insert into category (id, \"parentId\", name, \"uniqName\", \"childrenCount\", \"offersNum\", \"modelsNum\", visual, type) " +
                "values (:id, :parentId, :name, :uniqName, :childrenCount, :offersNum, :modelsNum, :visual, :type)";

        Map namedParameters = new HashMap();
        namedParameters.put("id", category.getId());
        namedParameters.put("parentId", category.getParentId());
        namedParameters.put("name", category.getName());
        namedParameters.put("uniqName", category.getUniqName());
        namedParameters.put("childrenCount", category.getChildrenCount());
        namedParameters.put("offersNum", category.getOffersNum());
        namedParameters.put("modelsNum", category.getModelsNum());
        namedParameters.put("visual", category.isVisual());
        namedParameters.put("type", category.getType());

        namedParameterJdbcTemplate.update(sql, namedParameters);
    }

    @Transactional
    public void createBatch(List<Category> categories) {
        String sql = "insert into category_tmp (id, \"parentId\", name, \"uniqName\", \"childrenCount\", \"offersNum\", \"modelsNum\", visual, type) " +
                "values (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                Category category = categories.get(i);
                int idx = 1;
                ps.setLong(idx++, category.getId());
                ps.setLong(idx++, category.getParentId());
                ps.setString(idx++, category.getName());
                ps.setString(idx++, category.getUniqName());
                ps.setInt(idx++, category.getChildrenCount());
                ps.setInt(idx++, category.getOffersNum());
                ps.setInt(idx++, category.getModelsNum());
                ps.setString(idx++, Boolean.toString(category.isVisual()));
                ps.setString(idx++, category.getType());
            }

            @Override
            public int getBatchSize() {
                return categories.size();
            }
        });
    }

    private class CategoryRowMapper implements RowMapper<Category> {
        @Override
        public Category mapRow(ResultSet resultSet, int i) throws SQLException {
            long id = resultSet.getLong("id");
            long parentId = resultSet.getLong("parentId");
            String name = resultSet.getString("name");
            String uniqName = resultSet.getString("uniqName");
            int childrenCount = resultSet.getInt("childrenCount");
            int offersNum = resultSet.getInt("offersNum");
            int modelsNum = resultSet.getInt("modelsNum");
            String visual = resultSet.getString("visual");
            String type = resultSet.getString("type");

            Category category = new Category(childrenCount, id, "", modelsNum, name, offersNum, parentId, type, uniqName, Boolean.valueOf(visual));

            return category;
        }
    }
}
