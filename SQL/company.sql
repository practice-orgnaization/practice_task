-- ============================================
-- 1. テーブル作成
-- ============================================

DROP TABLE IF EXISTS company;

CREATE TABLE company (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(130) NOT NULL,
    company_no VARCHAR(10) NOT NULL,
    department VARCHAR(50) NOT NULL,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP
        ON UPDATE CURRENT_TIMESTAMP
);

-- ============================================
-- 3. 全件取得
-- ============================================

SELECT *
FROM employees
ORDER BY id;

